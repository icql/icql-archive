package work.icql.ssm.mapper;

import work.icql.ssm.pojo.VwShiroAuth;
import work.icql.ssm.pojo.VwShiroAuthExample;

import java.util.List;

public interface VwShiroAuthMapper {
    long countByExample(VwShiroAuthExample example);

    List<VwShiroAuth> selectByExampleWithBLOBs(VwShiroAuthExample example);

    List<VwShiroAuth> selectByExample(VwShiroAuthExample example);
}