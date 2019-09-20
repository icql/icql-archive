package com.taotao.mapper;

import com.taotao.pojo.TbItemCatDO;
import com.taotao.pojo.TbItemCatDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbItemCatDOMapper {
    long countByExample(TbItemCatDOExample example);

    int deleteByExample(TbItemCatDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbItemCatDO record);

    int insertSelective(TbItemCatDO record);

    List<TbItemCatDO> selectByExample(TbItemCatDOExample example);

    TbItemCatDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItemCatDO record, @Param("example") TbItemCatDOExample example);

    int updateByExample(@Param("record") TbItemCatDO record, @Param("example") TbItemCatDOExample example);

    int updateByPrimaryKeySelective(TbItemCatDO record);

    int updateByPrimaryKey(TbItemCatDO record);
}