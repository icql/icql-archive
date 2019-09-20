package com.taotao.mapper;

import com.taotao.pojo.TbContentCategoryDO;
import com.taotao.pojo.TbContentCategoryDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbContentCategoryDOMapper {
    long countByExample(TbContentCategoryDOExample example);

    int deleteByExample(TbContentCategoryDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbContentCategoryDO record);

    int insertSelective(TbContentCategoryDO record);

    List<TbContentCategoryDO> selectByExample(TbContentCategoryDOExample example);

    TbContentCategoryDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbContentCategoryDO record, @Param("example") TbContentCategoryDOExample example);

    int updateByExample(@Param("record") TbContentCategoryDO record, @Param("example") TbContentCategoryDOExample example);

    int updateByPrimaryKeySelective(TbContentCategoryDO record);

    int updateByPrimaryKey(TbContentCategoryDO record);
}