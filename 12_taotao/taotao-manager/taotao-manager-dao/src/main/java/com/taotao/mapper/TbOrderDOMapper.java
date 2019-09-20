package com.taotao.mapper;

import com.taotao.pojo.TbOrderDO;
import com.taotao.pojo.TbOrderDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbOrderDOMapper {
    long countByExample(TbOrderDOExample example);

    int deleteByExample(TbOrderDOExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(TbOrderDO record);

    int insertSelective(TbOrderDO record);

    List<TbOrderDO> selectByExample(TbOrderDOExample example);

    TbOrderDO selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") TbOrderDO record, @Param("example") TbOrderDOExample example);

    int updateByExample(@Param("record") TbOrderDO record, @Param("example") TbOrderDOExample example);

    int updateByPrimaryKeySelective(TbOrderDO record);

    int updateByPrimaryKey(TbOrderDO record);
}