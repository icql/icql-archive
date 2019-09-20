package com.taotao.mapper;

import com.taotao.pojo.TbItemDO;
import com.taotao.pojo.TbItemDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbItemDOMapper {
    long countByExample(TbItemDOExample example);

    int deleteByExample(TbItemDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbItemDO record);

    int insertSelective(TbItemDO record);

    List<TbItemDO> selectByExample(TbItemDOExample example);

    TbItemDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItemDO record, @Param("example") TbItemDOExample example);

    int updateByExample(@Param("record") TbItemDO record, @Param("example") TbItemDOExample example);

    int updateByPrimaryKeySelective(TbItemDO record);

    int updateByPrimaryKey(TbItemDO record);
}