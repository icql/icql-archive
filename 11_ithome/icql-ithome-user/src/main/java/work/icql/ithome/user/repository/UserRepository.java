package work.icql.ithome.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import work.icql.ithome.user.entity.User;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/29 10:44
 * @Title UserRepository
 * @Description UserRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    User findByMobile(String mobile);
}
