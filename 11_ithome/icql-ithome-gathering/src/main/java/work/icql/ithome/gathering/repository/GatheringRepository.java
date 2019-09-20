package work.icql.ithome.gathering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import work.icql.ithome.gathering.entity.Gathering;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/26 13:01
 * @Title GatheringRepository
 * @Description GatheringRepository
 */
@Repository
public interface GatheringRepository extends JpaRepository<Gathering, String>, JpaSpecificationExecutor<Gathering> {

}
