package work.icql.api.service;

import work.icql.api.pojo.dto.BookmarkDataTreeNodeDTO;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/5 13:55
 * @Title BookmarkService
 * @Description BookmarkService
 */
public interface BookmarkService {

    /**
     * 根据当前token同步书签
     * @param token
     * @param version
     * @param bookmarkJson
     * @return
     */
    Object sync(String token, Integer version, String bookmarkJson);

    /**
     * 获取用户书签数据
     *
     * @param name
     * @param version
     * @return
     */
    List<BookmarkDataTreeNodeDTO> getBookmarkData(String name, Integer version);

    /**
     * 获取用户书签页面数据
     * @param name
     * @param version
     * @return
     */
    String getBookmarkPageData(String name, Integer version);
}
