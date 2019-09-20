package work.icql.ithome.spit.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import work.icql.ithome.spit.entity.Spit;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/28 9:31
 * @Title SpitRepository
 * @Description SpitRepository
 */
@Repository
public interface SpitRepository extends MongoRepository<Spit, String> {
    Page<Spit> findByParentid(String parentid, Pageable pageable);
}
