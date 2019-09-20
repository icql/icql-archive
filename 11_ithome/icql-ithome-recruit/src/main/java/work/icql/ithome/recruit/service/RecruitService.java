package work.icql.ithome.recruit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import work.icql.ithome.common.util.IdWorker;
import work.icql.ithome.recruit.entity.Recruit;
import work.icql.ithome.recruit.repository.RecruitRepository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/25 10:25
 * @Title RecruitService
 * @Description RecruitService
 */
@Service
public class RecruitService {
    @Autowired
    private RecruitRepository recruitRepository;

    @Autowired
    private IdWorker idWorker;

    public List<Recruit> findAll() {
        return recruitRepository.findAll();
    }

    public Recruit findById(String id) {
        return recruitRepository.findById(id).get();
    }

    public void add(Recruit recruit) {
        recruit.setId(idWorker.nextId() + "");//设置ID
        recruitRepository.save(recruit);
    }

    public void update(Recruit recruit) {
        recruitRepository.save(recruit);
    }

    public void deleteById(String id) {
        recruitRepository.deleteById(id);
    }


    public List<Recruit> findSearch(Recruit recruit) {
        Specification specification = createSpecification(recruit);
        return recruitRepository.findAll(specification);
    }

    public Page<Recruit> findSearch(Recruit recruit, int page, int size) {
        Specification specification = createSpecification(recruit);
        return recruitRepository.findAll(specification, PageRequest.of(page - 1, size));
    }

    public List<Recruit> recommend() {
        return recruitRepository.findTop4ByStateOrderByCreatetimeDesc("2");
    }

    public List<Recruit> newlist(){
        return recruitRepository.findTop12ByStateNotOrderByCreatetimeDesc("0");
    }

    private Specification<Recruit> createSpecification(Recruit recruit) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (!StringUtils.isEmpty(recruit.getId())) {
                predicateList.add(cb.equal(root.get("id").as(String.class), recruit.getId()));
            }
            if (!StringUtils.isEmpty(recruit.getJobname())) {
                predicateList.add(cb.equal(root.get("jobname").as(String.class), recruit.getJobname()));
            }
            if (!StringUtils.isEmpty(recruit.getSalary())) {
                predicateList.add(cb.equal(root.get("salary").as(String.class), recruit.getSalary()));
            }
            if (!StringUtils.isEmpty(recruit.getCondition())) {
                predicateList.add(cb.equal(root.get("condition").as(String.class), recruit.getCondition()));
            }
            if (!StringUtils.isEmpty(recruit.getEducation())) {
                predicateList.add(cb.equal(root.get("education").as(String.class), recruit.getEducation()));
            }
            if (!StringUtils.isEmpty(recruit.getType())) {
                predicateList.add(cb.equal(root.get("type").as(String.class), recruit.getType()));
            }
            if (!StringUtils.isEmpty(recruit.getAddress())) {
                predicateList.add(cb.equal(root.get("address").as(String.class), recruit.getAddress()));
            }
            if (!StringUtils.isEmpty(recruit.getEid())) {
                predicateList.add(cb.equal(root.get("eid").as(String.class), recruit.getEid()));
            }
            if (!StringUtils.isEmpty(recruit.getState())) {
                predicateList.add(cb.equal(root.get("state").as(String.class), recruit.getState()));
            }
            if (!StringUtils.isEmpty(recruit.getLabel())) {
                predicateList.add(cb.equal(root.get("label").as(String.class), recruit.getLabel()));
            }
            if (!StringUtils.isEmpty(recruit.getContent1())) {
                predicateList.add(cb.equal(root.get("content1").as(String.class), recruit.getContent1()));
            }
            if (!StringUtils.isEmpty(recruit.getContent2())) {
                predicateList.add(cb.equal(root.get("content2").as(String.class), recruit.getContent2()));
            }

            query.where(predicateList.toArray(new Predicate[predicateList.size()]));
            return query.getRestriction();
        };
    }
}
