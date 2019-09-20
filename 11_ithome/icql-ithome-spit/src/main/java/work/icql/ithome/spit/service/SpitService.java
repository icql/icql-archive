package work.icql.ithome.spit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import work.icql.ithome.common.entity.Result;
import work.icql.ithome.common.entity.StatusCode;
import work.icql.ithome.common.util.IdWorker;
import work.icql.ithome.spit.entity.Spit;
import work.icql.ithome.spit.repository.SpitRepository;

import java.util.Date;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/28 9:32
 * @Title SpitService
 * @Description SpitService
 */
@Service
public class SpitService {
    @Autowired
    private SpitRepository spitRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有标签
     *
     * @return
     */
    public List<Spit> findAll() {
        return spitRepository.findAll();
    }

    /**
     * 根据ID查找标签
     *
     * @param id
     * @return
     */
    public Spit findById(String id) {
        return spitRepository.findById(id).get();
    }

    /**
     * 增加
     *
     * @param spit
     */
    public void add(Spit spit) {
        spit.set_id(idWorker.nextId() + "");
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态

        //如果存在上级ID
        if (spit.getParentid() != null && !"".equals(spit.getParentid())) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }

        spitRepository.save(spit);
    }

    /**
     * 更新
     *
     * @param spit
     */
    public void update(Spit spit) {
        spitRepository.save(spit);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        spitRepository.deleteById(id);
    }

    /**
     * 根据父id查询
     *
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    public Page<Spit> findByParentid(String parentid, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return spitRepository.findByParentid(parentid, pageRequest);
    }

    /**
     * 点赞
     *
     * @param id
     */
    public Result updateThumbup(String id, String userid) {
        //判断用户是否点过赞
        if (redisTemplate.opsForValue().get("thumbup_" + userid + "_" + id) != null) {
            return new Result(false, StatusCode.REPERROR, "你已经点过赞了");
        }

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");
        return new Result(true, StatusCode.OK, "点赞成功");
    }

    /**
     * 更新浏览量
     * @param id
     */
    public void updateVisits(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("visits", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }

    /**
     * 更新分享数
     * @param id
     */
    public void updateShare(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("share", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }
}
