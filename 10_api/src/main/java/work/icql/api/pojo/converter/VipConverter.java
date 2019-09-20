package work.icql.api.pojo.converter;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import work.icql.api.pojo.dto.VipDTO;
import work.icql.api.pojo.entity.VipDO;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/6 14:50
 * @Title VipConverter
 * @Description VipConverter
 */
@Component
@Mapper(componentModel = "spring")
public interface VipConverter {
    /**
     * do2dto
     *
     * @param vipDO
     * @return
     */
    VipDTO do2dto(VipDO vipDO);

    /**
     * do2dto
     *
     * @param vipDOs
     * @return
     */
    List<VipDTO> do2dto(List<VipDO> vipDOs);

    /**
     * dto2do
     *
     * @param vipDTO
     * @return
     */
    VipDO dto2do(VipDTO vipDTO);

    /**
     * dto2do
     *
     * @param vipDTOs
     * @return
     */
    List<VipDO> dto2do(List<VipDTO> vipDTOs);
}
