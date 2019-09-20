package work.icql.ithome.qa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import work.icql.ithome.common.util.IdWorker;
import work.icql.ithome.qa.entity.Problem;
import work.icql.ithome.qa.repository.ProblemRepository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/25 10:56
 * @Title ProblemService
 * @Description ProblemService
 */
@Service
public class ProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private IdWorker idWorker;

    public List<Problem> findAll() {
        return problemRepository.findAll();
    }

    public Problem findById(String id) {
        return problemRepository.findById(id).get();
    }

    public void add(Problem problem) {
        problem.setId(idWorker.nextId() + "");//设置ID
        problemRepository.save(problem);
    }

    public void update(Problem problem) {
        problemRepository.save(problem);
    }

    public void deleteById(String id) {
        problemRepository.deleteById(id);
    }

    public List<Problem> findSearch(Problem problem) {
        Specification specification = createSpecification(problem);
        return problemRepository.findAll(specification);
    }

    public Page<Problem> findSearch(Problem problem, int page, int size) {
        Specification specification = createSpecification(problem);
        return problemRepository.findAll(specification, PageRequest.of(page - 1, size));
    }

    private Specification<Problem> createSpecification(Problem problem) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (!StringUtils.isEmpty(problem.getId())) {
                predicateList.add(cb.equal(root.get("id").as(String.class), problem.getId()));
            }
            if (!StringUtils.isEmpty(problem.getTitle())) {
                predicateList.add(cb.like(root.get("title").as(String.class), "%" + problem.getTitle() + "%"));
            }
            if (!StringUtils.isEmpty(problem.getContent())) {
                predicateList.add(cb.like(root.get("content").as(String.class), "%" + problem.getContent() + "%"));
            }
            query.where(predicateList.toArray(new Predicate[predicateList.size()]));
            return query.getRestriction();
        };
    }

    public Page<Problem> newlist(String labelid, int page, int size) {
        return problemRepository.findNewListByLabelId(labelid, PageRequest.of(page - 1, size));
    }

    public Page<Problem> hotlist(String labelid, int page, int size) {
        return problemRepository.findHotListByLabelId(labelid, PageRequest.of(page - 1, size));
    }

    public Page<Problem> waitlist(String labelid, int page, int size) {
        return problemRepository.findWaitListByLabelId(labelid, PageRequest.of(page - 1, size));
    }
}
