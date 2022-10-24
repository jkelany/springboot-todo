package com.jkelany.todo.api.cdm.mapper;

import com.jkelany.todo.api.cdm.dto.UserDTO;
import com.jkelany.todo.api.cdm.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDTO toDto(User item);

    List<UserDTO> toDtos(List<User> list);
}
