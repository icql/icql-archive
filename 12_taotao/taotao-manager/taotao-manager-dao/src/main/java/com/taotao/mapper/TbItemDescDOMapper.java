package com.taotao.mapper;

import com.taotao.pojo.TbItemDescDO;
import com.taotao.pojo.TbItemDescDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbItemDescDOMapper {
    long countByExample(TbItemDescDOExample example);

    int deleteByExample(TbItemDescDOExample example);

    int deleteByPrimaryKey(Long itemId);

    int insert(TbItemDescDO record);

    int insertSelective(TbItemDescDO record);

    List<TbItemDescDO> selectByExampleWithBLOBs(TbItemDescDOExample example);

    List<TbItemDescDO> selectByExample(TbItemDescDOExample example);

    TbItemDescDO selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") TbItemDescDO record, @Param("example") TbItemDescDOExample example);

    int updateByExampleWithBLOBs(@Param("record") TbItemDescDO record, @Param("example") TbItemDescDOExample example);

    int updateByExample(@Param("record") TbItemDescDO record, @Param("example") TbItemDescDOExample example);

    int updateByPrimaryKeySelective(TbItemDescDO record);

    int updateByPrimaryKeyWithBLOBs(TbItemDescDO record);

    int updateByPrimaryKey(TbItemDescDO record);
}