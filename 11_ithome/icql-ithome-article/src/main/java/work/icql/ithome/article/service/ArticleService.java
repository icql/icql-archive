package work.icql.ithome.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import work.icql.ithome.article.entity.Article;
import work.icql.ithome.article.repository.ArticleRepository;
import work.icql.ithome.common.util.IdWorker;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/25 10:56
 * @Title ArticleService
 * @Description ArticleService
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IdWorker idWorker;

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(String id) {
        //从缓存中提取
        Article article = (Article) redisTemplate.opsForValue().get("article_" + id);
        if (article == null) {
            article = articleRepository.findById(id).get();
            //保存进redis
            redisTemplate.opsForValue().set("article_" + id, article, 1, TimeUnit.DAYS);
        }
        return article;
    }

    public void add(Article article) {
        article.setId(idWorker.nextId() + "");//设置ID
        articleRepository.save(article);
    }

    public void update(Article article) {
        redisTemplate.delete("article_" + article.getId());
        articleRepository.save(article);
    }

    public void deleteById(String id) {
        redisTemplate.delete("article_" + id);//删除缓存
        articleRepository.deleteById(id);
    }

    public List<Article> findSearch(Article article) {
        Specification specification = createSpecification(article);
        return articleRepository.findAll(specification);
    }

    public Page<Article> findSearch(Article article, int page, int size) {
        Specification specification = createSpecification(article);
        return articleRepository.findAll(specification, PageRequest.of(page - 1, size));
    }

    private Specification<Article> createSpecification(Article article) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (!StringUtils.isEmpty(article.getId())) {
                predicateList.add(cb.equal(root.get("id").as(String.class), article.getId()));
            }
            query.where(predicateList.toArray(new Predicate[predicateList.size()]));
            return query.getRestriction();
        };
    }

    public int examine(String id) {
        return articleRepository.examine(id);
    }

    public int updateThumbup(String id) {
        return articleRepository.updateThumbup(id);
    }
}
