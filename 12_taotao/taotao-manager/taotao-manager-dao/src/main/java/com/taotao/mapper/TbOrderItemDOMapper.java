package com.taotao.mapper;

import com.taotao.pojo.TbOrderItemDO;
import com.taotao.pojo.TbOrderItemDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbOrderItemDOMapper {
    long countByExample(TbOrderItemDOExample example);

    int deleteByExample(TbOrderItemDOExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbOrderItemDO record);

    int insertSelective(TbOrderItemDO record);

    List<TbOrderItemDO> selectByExample(TbOrderItemDOExample example);

    TbOrderItemDO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbOrderItemDO record, @Param("example") TbOrderItemDOExample example);

    int updateByExample(@Param("record") TbOrderItemDO record, @Param("example") TbOrderItemDOExample example);

    int updateByPrimaryKeySelective(TbOrderItemDO record);

    int updateByPrimaryKey(TbOrderItemDO record);
}