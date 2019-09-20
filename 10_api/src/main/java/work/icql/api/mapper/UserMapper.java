package work.icql.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import work.icql.api.pojo.entity.UserDO;
import work.icql.api.pojo.entity.UserExample;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/23 14:10
 * @Title UserMapper
 * @Description UserMapper
 */
@Repository
public interface UserMapper {
    /**
     * 根据example计数
     *
     * @param example
     * @return
     */
    long countByExample(UserExample example);

    /**
     * 根据example删除
     *
     * @param example
     * @return
     */
    int deleteByExample(UserExample example);

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(UserDO record);

    /**
     * 有选择的新增
     *
     * @param record
     * @return
     */
    int insertSelective(UserDO record);

    /**
     * 根据example查询
     *
     * @param example
     * @return
     */
    List<UserDO> selectByExample(UserExample example);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    UserDO selectByPrimaryKey(Long id);

    /**
     * 根据name或email查询
     *
     * @param name
     * @param email
     * @return
     */
    UserDO selectByNameOrEmail(@Param("name") String name, @Param("email") String email);

    /**
     * 根据example有选择的更新
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") UserDO record, @Param("example") UserExample example);

    /**
     * 根据example更新
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") UserDO record, @Param("example") UserExample example);

    /**
     * 根据主键有选择的更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(UserDO record);

    /**
     * 根据主键更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserDO record);
}