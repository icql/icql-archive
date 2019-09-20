package com.taotao.mapper;

import com.taotao.pojo.TbItemParamItemDO;
import com.taotao.pojo.TbItemParamItemDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbItemParamItemDOMapper {
    long countByExample(TbItemParamItemDOExample example);

    int deleteByExample(TbItemParamItemDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbItemParamItemDO record);

    int insertSelective(TbItemParamItemDO record);

    List<TbItemParamItemDO> selectByExampleWithBLOBs(TbItemParamItemDOExample example);

    List<TbItemParamItemDO> selectByExample(TbItemParamItemDOExample example);

    TbItemParamItemDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItemParamItemDO record, @Param("example") TbItemParamItemDOExample example);

    int updateByExampleWithBLOBs(@Param("record") TbItemParamItemDO record, @Param("example") TbItemParamItemDOExample example);

    int updateByExample(@Param("record") TbItemParamItemDO record, @Param("example") TbItemParamItemDOExample example);

    int updateByPrimaryKeySelective(TbItemParamItemDO record);

    int updateByPrimaryKeyWithBLOBs(TbItemParamItemDO record);

    int updateByPrimaryKey(TbItemParamItemDO record);
}