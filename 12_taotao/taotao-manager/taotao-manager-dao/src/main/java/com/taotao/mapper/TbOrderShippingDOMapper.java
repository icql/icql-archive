package com.taotao.mapper;

import com.taotao.pojo.TbOrderShippingDO;
import com.taotao.pojo.TbOrderShippingDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbOrderShippingDOMapper {
    long countByExample(TbOrderShippingDOExample example);

    int deleteByExample(TbOrderShippingDOExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(TbOrderShippingDO record);

    int insertSelective(TbOrderShippingDO record);

    List<TbOrderShippingDO> selectByExample(TbOrderShippingDOExample example);

    TbOrderShippingDO selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") TbOrderShippingDO record, @Param("example") TbOrderShippingDOExample example);

    int updateByExample(@Param("record") TbOrderShippingDO record, @Param("example") TbOrderShippingDOExample example);

    int updateByPrimaryKeySelective(TbOrderShippingDO record);

    int updateByPrimaryKey(TbOrderShippingDO record);
}