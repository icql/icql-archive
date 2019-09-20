package com.taotao.mapper;

import com.taotao.pojo.TbContentDO;
import com.taotao.pojo.TbContentDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbContentDOMapper {
    long countByExample(TbContentDOExample example);

    int deleteByExample(TbContentDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbContentDO record);

    int insertSelective(TbContentDO record);

    List<TbContentDO> selectByExampleWithBLOBs(TbContentDOExample example);

    List<TbContentDO> selectByExample(TbContentDOExample example);

    TbContentDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbContentDO record, @Param("example") TbContentDOExample example);

    int updateByExampleWithBLOBs(@Param("record") TbContentDO record, @Param("example") TbContentDOExample example);

    int updateByExample(@Param("record") TbContentDO record, @Param("example") TbContentDOExample example);

    int updateByPrimaryKeySelective(TbContentDO record);

    int updateByPrimaryKeyWithBLOBs(TbContentDO record);

    int updateByPrimaryKey(TbContentDO record);
}