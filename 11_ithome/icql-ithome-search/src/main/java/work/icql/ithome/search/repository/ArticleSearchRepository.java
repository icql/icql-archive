package work.icql.ithome.search.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;
import work.icql.ithome.search.entity.ArticleSearch;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/28 14:59
 * @Title ArticleSearchRepository
 * @Description ArticleSearchRepository
 */
@Repository
public interface ArticleSearchRepository extends ElasticsearchCrudRepository<ArticleSearch, String> {
    Page<ArticleSearch> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
