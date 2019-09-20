package work.icql.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import work.icql.api.pojo.entity.VipDO;
import work.icql.api.pojo.entity.VipExample;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/23 14:10
 * @Title VipMapper
 * @Description VipMapper
 */
@Repository
public interface VipMapper {
    /**
     * 根据example计数
     *
     * @param example
     * @return
     */
    long countByExample(VipExample example);

    /**
     * 根据example删除
     *
     * @param example
     * @return
     */
    int deleteByExample(VipExample example);

    /***
     * 根据主键删除
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
    int insert(VipDO record);

    /**
     * 有选择的更新
     *
     * @param record
     * @return
     */
    int insertSelective(VipDO record);

    /**
     * 根据example查询
     *
     * @param example
     * @return
     */
    List<VipDO> selectByExample(VipExample example);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    VipDO selectByPrimaryKey(Long id);

    /**
     * 根据example有选择的更新
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") VipDO record, @Param("example") VipExample example);

    /**
     * 根据example更新
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") VipDO record, @Param("example") VipExample example);

    /**
     * 根据主键有选择的更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(VipDO record);

    /**
     * 根据主键更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(VipDO record);
}