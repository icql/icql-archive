package work.icql.ithome.label.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import work.icql.ithome.label.entity.Label;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/24 11:52
 * @Title LabelRepository
 * @Description LabelRepository
 */
@Repository
public interface LabelRepository extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label> {
}
