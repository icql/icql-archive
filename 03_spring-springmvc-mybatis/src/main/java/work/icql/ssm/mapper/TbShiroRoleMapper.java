package work.icql.ssm.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import work.icql.ssm.pojo.TbShiroRole;
import work.icql.ssm.pojo.TbShiroRoleExample;

public interface TbShiroRoleMapper {
    long countByExample(TbShiroRoleExample example);

    int deleteByExample(TbShiroRoleExample example);

    int deleteByPrimaryKey(String roleid);

    int insert(TbShiroRole record);

    int insertSelective(TbShiroRole record);

    List<TbShiroRole> selectByExample(TbShiroRoleExample example);

    TbShiroRole selectByPrimaryKey(String roleid);

    int updateByExampleSelective(@Param("record") TbShiroRole record, @Param("example") TbShiroRoleExample example);

    int updateByExample(@Param("record") TbShiroRole record, @Param("example") TbShiroRoleExample example);

    int updateByPrimaryKeySelective(TbShiroRole record);

    int updateByPrimaryKey(TbShiroRole record);
}