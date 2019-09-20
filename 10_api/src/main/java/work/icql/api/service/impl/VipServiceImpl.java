package work.icql.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import work.icql.api.common.util.IdWorker;
import work.icql.api.mapper.VipMapper;
import work.icql.api.pojo.converter.VipConverter;
import work.icql.api.pojo.dto.VipDTO;
import work.icql.api.pojo.entity.VipDO;
import work.icql.api.pojo.entity.VipExample;
import work.icql.api.service.VipService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/6 13:06
 * @Title VipServiceImpl
 * @Description VipServiceImpl
 */
@Slf4j
@Service
public class VipServiceImpl implements VipService {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    private VipMapper vipMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private VipConverter vipConverter;

    @Cacheable(cacheNames = "api:vip:message#86400", unless = "#result == null")
    @Override
    public String getMessage() {
        List<VipDO> content = getContent("0");
        if (content == null || content.size() == 0) {
            return null;
        }
        return content.get(0).getContent();
    }

    @Cacheable(cacheNames = "api:vip:instructions#86400", unless = "#result == null")
    @Override
    public String getInstructions() {
        List<VipDO> content = getContent("1");
        if (content == null || content.size() == 0) {
            return null;
        }
        return content.get(0).getContent();
    }

    @Cacheable(cacheNames = "api:vip:updatelog#86400", unless = "#result == null")
    @Override
    public String getUpdateLog() {
        List<VipDO> content = getContent("2");
        if (content == null || content.size() == 0) {
            return null;
        }
        return content.get(0).getContent();
    }

    @Cacheable(cacheNames = "api:vip:api#86400", unless = "#result == null")
    @Override
    public List<String> getApi() {
        List<VipDO> content = getContent("3");
        if (content == null || content.size() == 0) {
            return null;
        }
        List<String> result = new ArrayList<>();
        for (VipDO item : content) {
            result.add(item.getContent());
        }
        return result;
    }

    @CacheEvict(cacheNames = {"api:vip:message#86400", "api:vip:instructions#86400", "api:vip:updatelog#86400", "api:vip:api#86400"})
    @Override
    public void insertData(VipDTO vipDTO) {
        VipDO vip = vipConverter.dto2do(vipDTO);
        vip.setId(idWorker.nextId());
        vipMapper.insert(vip);
    }

    @CacheEvict(cacheNames = {"api:vip:message#86400", "api:vip:instructions#86400", "api:vip:updatelog#86400", "api:vip:api#86400"})
    @Override
    public void refresh() {
        log.info("刷新缓存");
    }

    private List<VipDO> getContent(String type) {
        VipExample example = new VipExample();
        VipExample.CriteriaBase criteria = example.createCriteria();
        criteria.andTypeEqualTo(Byte.valueOf(type));
        List<VipDO> vipDOS = vipMapper.selectByExample(example);
        if (vipDOS == null || vipDOS.size() == 0) {
            return null;
        }
        return vipDOS;
    }
}
