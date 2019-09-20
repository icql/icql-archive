package work.icql.ithome.qa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import work.icql.ithome.common.util.IdWorker;
import work.icql.ithome.qa.entity.Reply;
import work.icql.ithome.qa.repository.ReplyRepository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/25 10:25
 * @Title ReplyService
 * @Description ReplyService
 */
@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private IdWorker idWorker;

    public List<Reply> findAll() {
        return replyRepository.findAll();
    }

    public Reply findById(String id) {
        return replyRepository.findById(id).get();
    }

    public void add(Reply reply) {
        reply.setId(idWorker.nextId() + "");//设置ID
        replyRepository.save(reply);
    }

    public void update(Reply reply) {
        replyRepository.save(reply);
    }

    public void deleteById(String id) {
        replyRepository.deleteById(id);
    }

    public List<Reply> findSearch(Reply reply) {
        Specification specification = createSpecification(reply);
        return replyRepository.findAll(specification);
    }

    public Page<Reply> findSearch(Reply reply, int page, int size) {
        Specification specification = createSpecification(reply);
        return replyRepository.findAll(specification, PageRequest.of(page - 1, size));
    }

    private Specification<Reply> createSpecification(Reply reply) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (!StringUtils.isEmpty(reply.getId())) {
                predicateList.add(cb.equal(root.get("id").as(String.class), reply.getId()));
            }
            if (!StringUtils.isEmpty(reply.getProblemid())) {
                predicateList.add(cb.equal(root.get("problemid").as(String.class), reply.getProblemid()));
            }
            if (!StringUtils.isEmpty(reply.getContent())) {
                predicateList.add(cb.like(root.get("content").as(String.class), "%" + reply.getContent() + "%"));
            }

            query.where(predicateList.toArray(new Predicate[predicateList.size()]));
            return query.getRestriction();
        };
    }
}
