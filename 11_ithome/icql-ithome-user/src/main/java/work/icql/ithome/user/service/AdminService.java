package work.icql.ithome.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import work.icql.ithome.common.util.IdWorker;
import work.icql.ithome.user.entity.Admin;
import work.icql.ithome.user.repository.AdminRepository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/29 10:47
 * @Title AdminService
 * @Description AdminService
 */
@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin findById(String id) {
        return adminRepository.findById(id).get();
    }

    public void add(Admin admin) {
        admin.setId(idWorker.nextId() + "");//设置ID

        String newpassword = encoder.encode(admin.getPassword());//加密后
        admin.setPassword(newpassword);

        adminRepository.save(admin);
    }

    public void update(Admin admin) {
        adminRepository.save(admin);
    }

    public void deleteById(String id) {
        adminRepository.deleteById(id);
    }

    public List<Admin> findSearch(Admin admin) {
        Specification specification = createSpecification(admin);
        return adminRepository.findAll(specification);
    }

    /**
     * 分页多条件查询
     *
     * @param admin
     * @param page
     * @param size
     * @return
     */
    public Page<Admin> findSearch(Admin admin, int page, int size) {
        Specification specification = createSpecification(admin);
        return adminRepository.findAll(specification, PageRequest.of(page - 1, size));
    }

    /**
     * 构造查询条件
     *
     * @param admin
     * @return
     */
    private Specification<Admin> createSpecification(Admin admin) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (!StringUtils.isEmpty(admin.getLoginname())) {
                predicateList.add(cb.like(root.get("loginname").as(String.class), "%" + admin.getLoginname() + "%"));
            }
            query.where(predicateList.toArray(new Predicate[predicateList.size()]));
            //query.orderBy(cb.desc(root.get("adminname")),cb.asc(root.get("state")));
            return query.getRestriction();
        };
    }

    public Admin findByLoginnameAndPassword(String loginname, String password) {
        Admin admin = adminRepository.findByLoginname(loginname);
        if (admin != null && encoder.matches(password, admin.getPassword())) {
            return admin;
        } else {
            return null;
        }
    }
}
