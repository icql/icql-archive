package work.icql.ithome.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import work.icql.ithome.user.entity.Admin;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/29 10:45
 * @Title AdminRepository
 * @Description AdminRepository
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, String>, JpaSpecificationExecutor<Admin> {
    Admin findByLoginname(String loginname);
}
