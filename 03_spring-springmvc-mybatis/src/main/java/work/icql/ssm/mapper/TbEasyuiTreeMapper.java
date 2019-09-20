package work.icql.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import work.icql.ssm.pojo.TbEasyuiTree;
import work.icql.ssm.pojo.TbEasyuiTreeExample;

public interface TbEasyuiTreeMapper {
    long countByExample(TbEasyuiTreeExample example);

    int deleteByExample(TbEasyuiTreeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbEasyuiTree record);

    int insertSelective(TbEasyuiTree record);

    List<TbEasyuiTree> selectByExample(TbEasyuiTreeExample example);

    TbEasyuiTree selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbEasyuiTree record, @Param("example") TbEasyuiTreeExample example);

    int updateByExample(@Param("record") TbEasyuiTree record, @Param("example") TbEasyuiTreeExample example);

    int updateByPrimaryKeySelective(TbEasyuiTree record);

    int updateByPrimaryKey(TbEasyuiTree record);
}