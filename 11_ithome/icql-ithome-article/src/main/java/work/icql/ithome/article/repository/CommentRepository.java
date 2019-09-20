package work.icql.ithome.article.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import work.icql.ithome.article.entity.Comment;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/28 11:21
 * @Title CommentRepository
 * @Description CommentRepository
 */
@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByArticleid(String articleid);
}
