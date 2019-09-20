package work.icql.ithome.qa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import work.icql.ithome.qa.entity.Problem;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/25 10:16
 * @Title ProblemRepository
 * @Description ProblemRepository
 */
@Repository
public interface ProblemRepository extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {

    /**
     * 获取最新问答列表
     *
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT pro.* FROM tb_pl pl LEFT JOIN tb_problem pro ON (pl.problemid = pro.id) WHERE pl.labelid = ?1 ORDER BY pro.replytime DESC; ")
    Page<Problem> findNewListByLabelId(String labelId, Pageable pageable);

    /**
     * 获取热门问答列表（根据回复数）
     *
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT pro.* FROM tb_pl pl LEFT JOIN tb_problem pro ON (pl.problemid = pro.id) WHERE pl.labelid = ?1 ORDER BY pro.reply DESC; ")
    Page<Problem> findHotListByLabelId(String labelId, Pageable pageable);

    /**
     * 获取等待问答列表（根据回复数为0）
     *
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT pro.* FROM tb_pl pl LEFT JOIN tb_problem pro ON (pl.problemid = pro.id) WHERE pro.reply = 0 AND pl.labelid = ?1 ORDER BY pro.reply DESC; ")
    Page<Problem> findWaitListByLabelId(String labelId, Pageable pageable);
}
