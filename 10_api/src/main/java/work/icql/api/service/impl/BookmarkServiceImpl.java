package work.icql.api.service.impl;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import work.icql.api.common.exception.auth.AuthIsErrorException;
import work.icql.api.common.exception.auth.AuthIsExpiredException;
import work.icql.api.common.util.IdWorker;
import work.icql.api.common.util.JwtUtils;
import work.icql.api.config.JwtConfig;
import work.icql.api.mapper.BookmarkMapper;
import work.icql.api.mapper.UserMapper;
import work.icql.api.pojo.dto.BookmarkDataDTO;
import work.icql.api.pojo.dto.BookmarkDataResultDTO;
import work.icql.api.pojo.dto.BookmarkDataTreeNodeDTO;
import work.icql.api.pojo.entity.BookmarkDO;
import work.icql.api.pojo.entity.BookmarkExample;
import work.icql.api.pojo.entity.UserDO;
import work.icql.api.service.BookmarkService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/5 13:58
 * @Title BookmarkServiceImpl
 * @Description BookmarkServiceImpl
 */
@Service
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    private BookmarkMapper bookmarkMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private JwtConfig jwtConfig;

    /**
     * bookmark json串顶级数据id
     */
    List<String> topBookmarkDataIds = Arrays.asList(new String[]{"0", "1", "2"});

    @Override
    public Object sync(String token, Integer version, String bookmarkJson) {
        String lock = null;
        String userid = null;
        try {
            Claims claims = JwtUtils.parseToken(jwtConfig.getPublicKey(), token);
            userid = claims.get("id").toString();

            //作为锁对象，实现细化同步颗粒，只锁单个用户
            lock = ("lock" + userid).intern();
        } catch (ExpiredJwtException e) {
            //认证过期
            throw new AuthIsExpiredException();
        } catch (Exception e) {
            //认证失败
            throw new AuthIsErrorException();
        }

        //防止并发问题
        synchronized (lock) {
            BookmarkDO bookmarkDO = selectByUseridWithLatest(userid);

            //第一次同步
            if (bookmarkDO == null) {
                bookmarkDO = new BookmarkDO();
                bookmarkDO.setId(idWorker.nextId());
                bookmarkDO.setUserid(Long.valueOf(userid));
                bookmarkDO.setData(bookmarkJson);
                bookmarkDO.setVersion(1);
                insert(bookmarkDO);

                return 1;
            }

            String dbData = bookmarkDO.getData();
            Integer dbVersion = bookmarkDO.getVersion();

            BookmarkDataDTO dbBookmarkDataDTO = JSON.parseObject(dbData.substring(1, dbData.length() - 1), BookmarkDataDTO.class);

            //版本号小于数据库里的最新版本，返回最新数据
            if (version < dbVersion) {
                List<BookmarkDataTreeNodeDTO> bookmarkDataTreeNodeDTOS = new ArrayList<>();

                //解析
                getBookmarkDataTreeNodeDTO(bookmarkDataTreeNodeDTOS, dbBookmarkDataDTO);

                //排序
                sort(bookmarkDataTreeNodeDTOS);

                BookmarkDataResultDTO bookmarkDataResultDTO = new BookmarkDataResultDTO();
                bookmarkDataResultDTO.setVersion(dbVersion);
                bookmarkDataResultDTO.setTreeNodeDTOS(bookmarkDataTreeNodeDTOS);
                return bookmarkDataResultDTO;
            }

            //版本号大于数据库里的最新版本，这种情况一般不存在，但是手动更改数据库时有可能发生
            //这时以数据库最新的版本为准，等待下次同步
            if (version > bookmarkDO.getVersion()) {
                return 0;
            }

            //版本号等于数据库里的最新版本，再比较bookmarkJson
            BookmarkDataDTO newBookmarkDataDTO = JSON.parseObject(bookmarkJson.substring(1, bookmarkJson.length() - 1), BookmarkDataDTO.class);
            if (!newBookmarkDataDTO.equals(dbBookmarkDataDTO)) {
                bookmarkDO.setId(idWorker.nextId());
                bookmarkDO.setUserid(Long.valueOf(userid));
                bookmarkDO.setData(bookmarkJson);
                int newVersion = version + 1;
                bookmarkDO.setVersion(newVersion);
                insert(bookmarkDO);
                return newVersion;
            }
        }

        return null;
    }

    @Override
    public List<BookmarkDataTreeNodeDTO> getBookmarkData(String name, Integer version) {
        UserDO userDO = userMapper.selectByNameOrEmail(name, name);
        if (userDO == null) {
            return null;
        }
        BookmarkDO bookmarkDO = null;
        if (version == null) {
            bookmarkDO = selectByUseridWithLatest(userDO.getId().toString());
        } else {
            BookmarkExample example = new BookmarkExample();
            BookmarkExample.CriteriaBase criteria = example.createCriteria();
            criteria.andUseridEqualTo(userDO.getId());
            criteria.andVersionEqualTo(version);

            List<BookmarkDO> bookmarkDOS = bookmarkMapper.selectByExampleWithBLOBs(example);
            if (bookmarkDOS == null || bookmarkDOS.size() == 0) {
                return null;
            }
            bookmarkDO = bookmarkDOS.get(0);
        }

        if (bookmarkDO == null) {
            return null;
        }

        String data = bookmarkDO.getData();
        BookmarkDataDTO dbBookmarkDataDTO = JSON.parseObject(data.substring(1, data.length() - 1), BookmarkDataDTO.class);
        List<BookmarkDataTreeNodeDTO> bookmarkDataTreeNodeDTOS = new ArrayList<>();
        //解析
        getBookmarkDataTreeNodeDTO(bookmarkDataTreeNodeDTOS, dbBookmarkDataDTO);
        //排序
        sort(bookmarkDataTreeNodeDTOS);

        return bookmarkDataTreeNodeDTOS;
    }

    @Override
    public String getBookmarkPageData(String name, Integer version) {
        StringBuilder treeHtml = new StringBuilder();

        List<BookmarkDataTreeNodeDTO> bookmarkData = getBookmarkData(name, version);
        String title = String.format("[%s]的书签%s版", name, (version == null ? "最新" : ("第" + version)));
        BookmarkDataTreeNodeDTO rootNode = new BookmarkDataTreeNodeDTO();
        rootNode.setId("1");
        rootNode.setTitle(title);
        generateTreeHtml(bookmarkData, rootNode, treeHtml);

        return treeHtml.toString();
    }

    @Cacheable(cacheNames = "api:bookmark#7200", key = "#{userid}", unless = "#result == null")
    public BookmarkDO selectByUseridWithLatest(String userid) {
        return bookmarkMapper.selectByUseridWithLatest(Long.valueOf(userid));
    }

    @CacheEvict(cacheNames = "api:bookmark#7200", key = "#{bookmarkDO.userid}")
    public void insert(BookmarkDO bookmarkDO) {
        bookmarkMapper.insert(bookmarkDO);
    }

    /**
     * 排序BookmarkDataTreeNodeDTO
     * @param bookmarkDataTreeNodeDTOS
     */
    private void sort(List<BookmarkDataTreeNodeDTO> bookmarkDataTreeNodeDTOS) {
        bookmarkDataTreeNodeDTOS.sort((o1, o2) -> {
            int intp1 = Integer.valueOf(o1.getParentId());
            int intp2 = Integer.valueOf(o2.getParentId());
            if (intp1 > intp2) {
                return 1;
            } else if (intp1 < intp2) {
                return -1;
            }
            if (o1.getIndex() > o2.getIndex()) {
                return 1;
            } else if (o1.getIndex() < o2.getIndex()) {
                return -1;
            }
            return 0;
        });
    }

    /**
     * 解析BookmarkDataTreeNodeDTO
     * @param bookmarkDataTreeNodeDTOS
     * @param bookmarkDataDTO
     */
    private void getBookmarkDataTreeNodeDTO(List<BookmarkDataTreeNodeDTO> bookmarkDataTreeNodeDTOS, BookmarkDataDTO bookmarkDataDTO) {
        BookmarkDataDTO[] children = bookmarkDataDTO.getChildren();
        if (children != null && children.length > 0) {
            for (BookmarkDataDTO data : children) {
                getBookmarkDataTreeNodeDTO(bookmarkDataTreeNodeDTOS, data);
            }
        }
        if (topBookmarkDataIds.contains(bookmarkDataDTO.getId())) {
            return;
        }
        BookmarkDataTreeNodeDTO bookmarkDataTreeNodeDTO = new BookmarkDataTreeNodeDTO();
        bookmarkDataTreeNodeDTO.setId(bookmarkDataDTO.getId());
        bookmarkDataTreeNodeDTO.setIndex(bookmarkDataDTO.getIndex());
        bookmarkDataTreeNodeDTO.setParentId(bookmarkDataDTO.getParentId());
        bookmarkDataTreeNodeDTO.setTitle(bookmarkDataDTO.getTitle());
        bookmarkDataTreeNodeDTO.setUrl(bookmarkDataDTO.getUrl());
        bookmarkDataTreeNodeDTOS.add(bookmarkDataTreeNodeDTO);
    }

    /**
     * 获取treeHtml
     * @param bookmarkData
     * @param data
     * @param treeHtml
     */
    private void generateTreeHtml(List<BookmarkDataTreeNodeDTO> bookmarkData, BookmarkDataTreeNodeDTO data, StringBuilder treeHtml) {
        if (data == null) {
            return;
        }
        if (!StringUtils.isEmpty(data.getUrl())) {
            treeHtml.append(String.format("<li><span><i class='icon-leaf'></i><a href='%s' target='_blank'>%s</a></span></li>", data.getUrl(), data.getTitle()));
            return;
        }
        treeHtml.append(String.format("<li><span><i class='icon-folder-open'></i>%s</span><ul>", data.getTitle()));
        for (BookmarkDataTreeNodeDTO item : bookmarkData) {
            if (data.getId().equals(item.getParentId())) {
                generateTreeHtml(bookmarkData, item, treeHtml);
            }
        }
        treeHtml.append("</ul></li>");
    }
}
