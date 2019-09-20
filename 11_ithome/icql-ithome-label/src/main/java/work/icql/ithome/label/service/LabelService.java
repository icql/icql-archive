package work.icql.ithome.label.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import work.icql.ithome.common.util.IdWorker;
import work.icql.ithome.label.entity.Label;
import work.icql.ithome.label.repository.LabelRepository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/24 11:54
 * @Title LabelService
 * @Description LabelService
 */
@Service
public class LabelService {
    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有标签
     *
     * @return
     */
    public List<Label> findAll() {
        return labelRepository.findAll();
    }

    /**
     * 根据ID查找标签
     *
     * @param id
     * @return
     */
    public Label findById(String id) {
        return labelRepository.findById(id).get();
    }

    /**
     * 增加
     *
     * @param label
     */
    public void add(Label label) {
        label.setId(idWorker.nextId() + "");//设置ID
        labelRepository.save(label);
    }

    /**
     * 更新
     *
     * @param label
     */
    public void update(Label label) {
        labelRepository.save(label);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        labelRepository.deleteById(id);
    }

    /**
     * 多条件查询
     *
     * @param label
     * @return
     */
    public List<Label> findSearch(Label label) {
        Specification specification = createSpecification(label);
        return labelRepository.findAll(specification);
    }

    /**
     * 分页多条件查询
     *
     * @param label
     * @param page
     * @param size
     * @return
     */
    public Page<Label> findSearch(Label label, int page, int size) {
        Specification specification = createSpecification(label);
        return labelRepository.findAll(specification, PageRequest.of(page - 1, size));
    }

    /**
     * 构造查询条件
     *
     * @param label
     * @return
     */
    private Specification<Label> createSpecification(Label label) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (!StringUtils.isEmpty(label.getLabelname())) {
                predicateList.add(cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%"));
            }
            if (!StringUtils.isEmpty(label.getState())) {
                predicateList.add(cb.equal(root.get("state").as(String.class), label.getState()));
            }
            if (!StringUtils.isEmpty(label.getRecommend())) {
                predicateList.add(cb.like(root.get("recommend").as(String.class), "%" + label.getRecommend() + "%"));
            }
            query.where(predicateList.toArray(new Predicate[predicateList.size()]));
            //query.orderBy(cb.desc(root.get("labelname")),cb.asc(root.get("state")));
            return query.getRestriction();
        };
    }
}
