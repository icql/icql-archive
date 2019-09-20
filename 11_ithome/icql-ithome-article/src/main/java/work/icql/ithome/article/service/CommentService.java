package work.icql.ithome.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import work.icql.ithome.article.entity.Comment;
import work.icql.ithome.article.repository.CommentRepository;
import work.icql.ithome.common.util.IdWorker;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/28 11:22
 * @Title CommentRespository
 * @Description CommentRespository
 */
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有标签
     *
     * @return
     */
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    /**
     * 根据ID查找标签
     *
     * @param id
     * @return
     */
    public Comment findById(String id) {
        return commentRepository.findById(id).get();
    }

    /**
     * 增加
     *
     * @param comment
     */
    public void add(Comment comment) {
        comment.set_id(idWorker.nextId() + "");
        commentRepository.save(comment);
    }

    /**
     * 更新
     *
     * @param comment
     */
    public void update(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }

    /**
     * 根据文章id获取评论列表
     *
     * @param articleid
     * @return
     */
    public List<Comment> findByArticleid(String articleid) {
        return commentRepository.findByArticleid(articleid);
    }
}
