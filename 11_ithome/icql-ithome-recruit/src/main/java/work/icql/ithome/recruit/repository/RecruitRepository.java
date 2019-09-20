package work.icql.ithome.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import work.icql.ithome.recruit.entity.Recruit;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/25 10:17
 * @Title RecruitRepository
 * @Description RecruitRepository
 */
@Repository
public interface RecruitRepository extends JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit> {

    List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);

    List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
}
