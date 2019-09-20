package work.icql.ssm.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import work.icql.ssm.pojo.TbShiroFunc;
import work.icql.ssm.pojo.TbShiroFuncExample;

public interface TbShiroFuncMapper {
    long countByExample(TbShiroFuncExample example);

    int deleteByExample(TbShiroFuncExample example);

    int deleteByPrimaryKey(String funcid);

    int insert(TbShiroFunc record);

    int insertSelective(TbShiroFunc record);

    List<TbShiroFunc> selectByExample(TbShiroFuncExample example);

    TbShiroFunc selectByPrimaryKey(String funcid);

    int updateByExampleSelective(@Param("record") TbShiroFunc record, @Param("example") TbShiroFuncExample example);

    int updateByExample(@Param("record") TbShiroFunc record, @Param("example") TbShiroFuncExample example);

    int updateByPrimaryKeySelective(TbShiroFunc record);

    int updateByPrimaryKey(TbShiroFunc record);
}