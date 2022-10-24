package com.jkelany.todo.api.cdm.mapper;

import com.jkelany.todo.api.cdm.dto.TodoDTO;
import com.jkelany.todo.api.cdm.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TodoMapper {

    TodoDTO toDto(Todo item);

    Todo fromDto(TodoDTO item);

    List<TodoDTO> toDtos(List<Todo> list);
}
