package work.icql.ssm.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import work.icql.ssm.pojo.TbShiroUser;
import work.icql.ssm.pojo.TbShiroUserExample;

public interface TbShiroUserMapper {
    long countByExample(TbShiroUserExample example);

    int deleteByExample(TbShiroUserExample example);

    int deleteByPrimaryKey(String userid);

    int insert(TbShiroUser record);

    int insertSelective(TbShiroUser record);

    List<TbShiroUser> selectByExampleWithBLOBs(TbShiroUserExample example);

    List<TbShiroUser> selectByExample(TbShiroUserExample example);

    TbShiroUser selectByPrimaryKey(String userid);

    int updateByExampleSelective(@Param("record") TbShiroUser record, @Param("example") TbShiroUserExample example);

    int updateByExampleWithBLOBs(@Param("record") TbShiroUser record, @Param("example") TbShiroUserExample example);

    int updateByExample(@Param("record") TbShiroUser record, @Param("example") TbShiroUserExample example);

    int updateByPrimaryKeySelective(TbShiroUser record);

    int updateByPrimaryKeyWithBLOBs(TbShiroUser record);

    int updateByPrimaryKey(TbShiroUser record);
}