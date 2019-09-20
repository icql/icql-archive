package work.icql.ssm.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import work.icql.ssm.pojo.TbSSOUser;
import work.icql.ssm.pojo.TbSSOUserExample;

public interface TbSSOUserMapper {
    long countByExample(TbSSOUserExample example);

    int deleteByExample(TbSSOUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbSSOUser record);

    int insertSelective(TbSSOUser record);

    List<TbSSOUser> selectByExample(TbSSOUserExample example);

    TbSSOUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbSSOUser record, @Param("example") TbSSOUserExample example);

    int updateByExample(@Param("record") TbSSOUser record, @Param("example") TbSSOUserExample example);

    int updateByPrimaryKeySelective(TbSSOUser record);

    int updateByPrimaryKey(TbSSOUser record);
}