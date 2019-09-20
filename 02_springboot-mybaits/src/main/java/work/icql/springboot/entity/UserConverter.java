package work.icql.springboot.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

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
    @Mappings({
            @Mapping(target = "deleted", expression = "java(user.getDeleted() == 1 ? \"是\" : \"否\")")
    })
    UserDTO do2dto(User user);

    /**
     * 不用map，会自动调用上面的方法进行转换
     *
     * @param users
     * @return
     */
    List<User> do2dto(List<User> users);
}
