package work.icql.ithome.recruit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import work.icql.ithome.common.util.IdWorker;
import work.icql.ithome.recruit.entity.Enterprise;
import work.icql.ithome.recruit.repository.EnterpriseRepository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/25 10:56
 * @Title EnterpriseService
 * @Description EnterpriseService
 */
@Service
public class EnterpriseService {
    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private IdWorker idWorker;

    public List<Enterprise> findAll() {
        return enterpriseRepository.findAll();
    }

    public Enterprise findById(String id) {
        return enterpriseRepository.findById(id).get();
    }

    public void add(Enterprise enterprise) {
        enterprise.setId(idWorker.nextId() + "");//设置ID
        enterpriseRepository.save(enterprise);
    }

    public void update(Enterprise enterprise) {
        enterpriseRepository.save(enterprise);
    }

    public void deleteById(String id) {
        enterpriseRepository.deleteById(id);
    }

    public List<Enterprise> findSearch(Enterprise enterprise) {
        Specification specification = createSpecification(enterprise);
        return enterpriseRepository.findAll(specification);
    }

    public Page<Enterprise> findSearch(Enterprise enterprise, int page, int size) {
        Specification specification = createSpecification(enterprise);
        return enterpriseRepository.findAll(specification, PageRequest.of(page - 1, size));
    }

    public List<Enterprise> hotlist(){
        return enterpriseRepository.findByIshot("1");
    }

    private Specification<Enterprise> createSpecification(Enterprise enterprise) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (!StringUtils.isEmpty(enterprise.getId())) {
                predicateList.add(cb.equal(root.get("id").as(String.class), enterprise.getId()));
            }
            if (!StringUtils.isEmpty(enterprise.getName())) {
                predicateList.add(cb.equal(root.get("name").as(String.class), enterprise.getName()));
            }
            if (!StringUtils.isEmpty(enterprise.getSummary())) {
                predicateList.add(cb.equal(root.get("summary").as(String.class), enterprise.getSummary()));
            }
            if (!StringUtils.isEmpty(enterprise.getAddress())) {
                predicateList.add(cb.equal(root.get("address").as(String.class), enterprise.getAddress()));
            }
            if (!StringUtils.isEmpty(enterprise.getCoordinate())) {
                predicateList.add(cb.equal(root.get("coordinate").as(String.class), enterprise.getCoordinate()));
            }
            if (!StringUtils.isEmpty(enterprise.getIshot())) {
                predicateList.add(cb.equal(root.get("ishot").as(String.class), enterprise.getIshot()));
            }
            if (!StringUtils.isEmpty(enterprise.getAddress())) {
                predicateList.add(cb.equal(root.get("address").as(String.class), enterprise.getAddress()));
            }
            if (!StringUtils.isEmpty(enterprise.getLogo())) {
                predicateList.add(cb.equal(root.get("logo").as(String.class), enterprise.getLogo()));
            }
            if (!StringUtils.isEmpty(enterprise.getJobcount())) {
                predicateList.add(cb.equal(root.get("jobcount").as(String.class), enterprise.getJobcount()));
            }
            if (!StringUtils.isEmpty(enterprise.getUrl())) {
                predicateList.add(cb.equal(root.get("url").as(String.class), enterprise.getUrl()));
            }
            query.where(predicateList.toArray(new Predicate[predicateList.size()]));
            return query.getRestriction();
        };
    }
}
