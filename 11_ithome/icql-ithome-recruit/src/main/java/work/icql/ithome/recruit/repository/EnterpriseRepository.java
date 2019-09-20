package work.icql.ithome.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import work.icql.ithome.recruit.entity.Enterprise;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/25 10:16
 * @Title EnterpriseRepository
 * @Description EnterpriseRepository
 */
@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, String>, JpaSpecificationExecutor<Enterprise> {

    List<Enterprise> findByIshot(String isHot);
}
