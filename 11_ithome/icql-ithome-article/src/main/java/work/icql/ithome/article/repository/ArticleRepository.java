package work.icql.ithome.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import work.icql.ithome.article.entity.Article;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/25 10:16
 * @Title ArticleRepository
 * @Description ArticleRepository
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {
    /**
     * 审核
     *
     * @param id
     */
    @Modifying
    @Query(nativeQuery = true, value = "update Article set state = '1' where id = ?1 ;")
    int examine(String id);

    /**
     * 点赞
     *
     * @param id
     * @return
     */
    @Modifying
    @Query(nativeQuery = true, value = "update Article a set thumbup = thumbup + 1 where id = ?1;")
    int updateThumbup(String id);
}
