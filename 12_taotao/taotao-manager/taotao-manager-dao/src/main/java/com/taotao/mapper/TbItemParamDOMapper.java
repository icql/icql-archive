package com.taotao.mapper;

import com.taotao.pojo.TbItemParamDO;
import com.taotao.pojo.TbItemParamDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbItemParamDOMapper {
    long countByExample(TbItemParamDOExample example);

    int deleteByExample(TbItemParamDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbItemParamDO record);

    int insertSelective(TbItemParamDO record);

    List<TbItemParamDO> selectByExampleWithBLOBs(TbItemParamDOExample example);

    List<TbItemParamDO> selectByExample(TbItemParamDOExample example);

    TbItemParamDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItemParamDO record, @Param("example") TbItemParamDOExample example);

    int updateByExampleWithBLOBs(@Param("record") TbItemParamDO record, @Param("example") TbItemParamDOExample example);

    int updateByExample(@Param("record") TbItemParamDO record, @Param("example") TbItemParamDOExample example);

    int updateByPrimaryKeySelective(TbItemParamDO record);

    int updateByPrimaryKeyWithBLOBs(TbItemParamDO record);

    int updateByPrimaryKey(TbItemParamDO record);
}