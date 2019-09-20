package work.icql.ithome.qa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import work.icql.ithome.qa.entity.Reply;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/25 10:17
 * @Title ReplyRepository
 * @Description ReplyRepository
 */
@Repository
public interface ReplyRepository extends JpaRepository<Reply, String>, JpaSpecificationExecutor<Reply> {
}
