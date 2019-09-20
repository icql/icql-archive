package work.icql.ssm.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import work.icql.ssm.pojo.TbEasyuiGrid;
import work.icql.ssm.pojo.TbEasyuiGridExample;

public interface TbEasyuiGridMapper {
    long countByExample(TbEasyuiGridExample example);

    int deleteByExample(TbEasyuiGridExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbEasyuiGrid record);

    int insertSelective(TbEasyuiGrid record);

    List<TbEasyuiGrid> selectByExample(TbEasyuiGridExample example);

    TbEasyuiGrid selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbEasyuiGrid record, @Param("example") TbEasyuiGridExample example);

    int updateByExample(@Param("record") TbEasyuiGrid record, @Param("example") TbEasyuiGridExample example);

    int updateByPrimaryKeySelective(TbEasyuiGrid record);

    int updateByPrimaryKey(TbEasyuiGrid record);
}