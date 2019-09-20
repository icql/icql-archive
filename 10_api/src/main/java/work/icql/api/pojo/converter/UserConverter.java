package work.icql.api.pojo.converter;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import work.icql.api.pojo.dto.UserDTO;
import work.icql.api.pojo.entity.UserDO;

import java.util.List;


/**
 * @author icql
 * @version 1.0
 * @date 2019/3/28 14:20
 * @Title UserConverter
 * @Description UserConverter
 */
@Component
@Mapper(componentModel = "spring")
public interface UserConverter {

    /**
     * do2dto
     * @param userDO
     * @return
     */
    UserDTO do2dto(UserDO userDO);

    /**
     * do2dto
     * @param userDOs
     * @return
     */
    List<UserDTO> do2dto(List<UserDO> userDOs);

    /**
     * dto2do
     * @param userDTO
     * @return
     */
    UserDO dto2do(UserDTO userDTO);

    /**
     * dto2do
     * @param userDTOs
     * @return
     */
    List<UserDO> dto2do(List<UserDTO> userDTOs);
}
