package com.taotao.mapper;

import com.taotao.pojo.TbUserDO;
import com.taotao.pojo.TbUserDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserDOMapper {
    long countByExample(TbUserDOExample example);

    int deleteByExample(TbUserDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbUserDO record);

    int insertSelective(TbUserDO record);

    List<TbUserDO> selectByExample(TbUserDOExample example);

    TbUserDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbUserDO record, @Param("example") TbUserDOExample example);

    int updateByExample(@Param("record") TbUserDO record, @Param("example") TbUserDOExample example);

    int updateByPrimaryKeySelective(TbUserDO record);

    int updateByPrimaryKey(TbUserDO record);
}