package work.icql.ithome.gathering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import work.icql.ithome.common.util.IdWorker;
import work.icql.ithome.gathering.entity.Gathering;
import work.icql.ithome.gathering.repository.GatheringRepository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/24 11:54
 * @Title GatheringService
 * @Description GatheringService
 */
@Service
public class GatheringService {
    @Autowired
    private GatheringRepository gatheringRepository;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有标签
     *
     * @return
     */
    public List<Gathering> findAll() {
        return gatheringRepository.findAll();
    }

    /**
     * 根据ID查找标签
     *
     * @param id
     * @return
     */
    @Cacheable(value="gathering",key="#id")
    public Gathering findById(String id) {
        return gatheringRepository.findById(id).get();
    }

    /**
     * 增加
     *
     * @param gathering
     */
    public void add(Gathering gathering) {
        gathering.setId(idWorker.nextId() + "");//设置ID
        gatheringRepository.save(gathering);
    }

    /**
     * 更新
     *
     * @param gathering
     */
    @CacheEvict(value="gathering",key="#gathering.id")
    public void update(Gathering gathering) {
        gatheringRepository.save(gathering);
    }

    /**
     * 删除
     *
     * @param id
     */
    @CacheEvict(value="gathering",key="#id")
    public void deleteById(String id) {
        gatheringRepository.deleteById(id);
    }

    /**
     * 多条件查询
     *
     * @param gathering
     * @return
     */
    public List<Gathering> findSearch(Gathering gathering) {
        Specification specification = createSpecification(gathering);
        return gatheringRepository.findAll(specification);
    }

    /**
     * 分页多条件查询
     *
     * @param gathering
     * @param page
     * @param size
     * @return
     */
    public Page<Gathering> findSearch(Gathering gathering, int page, int size) {
        Specification specification = createSpecification(gathering);
        return gatheringRepository.findAll(specification, PageRequest.of(page - 1, size));
    }

    /**
     * 构造查询条件
     *
     * @param gathering
     * @return
     */
    private Specification<Gathering> createSpecification(Gathering gathering) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (!StringUtils.isEmpty(gathering.getId())) {
                predicateList.add(cb.equal(root.get("id").as(String.class), gathering.getId()));
            }
            if (!StringUtils.isEmpty(gathering.getName())) {
                predicateList.add(cb.like(root.get("name").as(String.class), "%" + gathering.getName() + "%"));
            }
            if (!StringUtils.isEmpty(gathering.getSummary())) {
                predicateList.add(cb.like(root.get("summary").as(String.class), "%" + gathering.getSummary() + "%"));
            }
            if (!StringUtils.isEmpty(gathering.getDetail())) {
                predicateList.add(cb.like(root.get("detail").as(String.class), "%" + gathering.getDetail() + "%"));
            }
            query.where(predicateList.toArray(new Predicate[predicateList.size()]));
            return query.getRestriction();
        };
    }
}
