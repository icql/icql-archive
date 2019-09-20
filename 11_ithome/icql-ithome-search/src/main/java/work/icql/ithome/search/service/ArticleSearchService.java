package work.icql.ithome.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import work.icql.ithome.search.entity.ArticleSearch;
import work.icql.ithome.search.repository.ArticleSearchRepository;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/28 15:01
 * @Title ArticleSearchService
 * @Description ArticleSearchService
 */
@Service
public class ArticleSearchService {
    @Autowired
    private ArticleSearchRepository articleSearchRepository;

    /**
     * 增加文章
     *
     * @param articleSearch
     */
    public void save(ArticleSearch articleSearch) {
        articleSearchRepository.save(articleSearch);
    }

    /**
     * 搜索文章
     *
     * @param keywords
     * @param page
     * @param size
     * @return
     */
    public Page<ArticleSearch> findByTitleLike(String keywords, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return articleSearchRepository.findByTitleOrContentLike(keywords, keywords, pageRequest);
    }
}