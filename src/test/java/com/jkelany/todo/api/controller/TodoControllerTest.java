package com.jkelany.todo.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jkelany.todo.api.cdm.entity.Todo;
import com.jkelany.todo.api.cdm.mapper.TodoMapperImpl;
import com.jkelany.todo.api.service.TodoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TodoController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@Import(TodoMapperImpl.class)
class TodoControllerTest extends BaseControllerTest {

    @MockBean
    private TodoService todoService;

    @Test
    @DisplayName("GET /todo")
    public void whenGetAll_ReturnJsonArray() throws Exception {
        Todo todo1 = new Todo("Todo1", "Todo1 Desc", "1");
        Todo todo2 = new Todo("Todo2", "Todo2 Desc", "1");
        List<Todo> list = Arrays.asList(todo1, todo2);
        Page<Todo> pageTodo = new PageImpl<>(list);

        when(todoService.getAll(anyString(), any())).thenReturn(pageTodo);

        mockMvc.perform(doGet("/todo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].title", equalTo(todo1.getTitle())));
    }

    @Test
    @DisplayName("GET /todo/<id>")
    public void whenGetById_ReturnJsonObject() throws Exception {
        Todo todo1 = new Todo("Todo1", "Todo1 Desc", "1");
        when(todoService.get(anyString())).thenReturn(todo1);

        mockMvc.perform(doGet("/todo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo(todo1.getTitle())));
    }

    @Test
    @DisplayName("POST /todo")
    public void whenCreate_SaveToDb() throws Exception {
        Todo todo1 = new Todo("Todo1", "Todo1 Desc", "1");
        todo1.setId("1");
        todo1.setIpAddress("172.0.0.1");

        when(todoService.insert(Mockito.any(Todo.class))).thenReturn(todo1);

        mockMvc.perform(doPost("/todo").content(new ObjectMapper().writeValueAsString(todo1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", equalTo(todo1.getTitle())));
    }

    @Test
    @DisplayName("DELETE /todo/<id>")
    public void whenDeleteById_DeleteFromDb() throws Exception {
        Todo todo1 = new Todo("Todo1", "Todo1 Desc", "1");
        when(todoService.get(anyString())).thenReturn(todo1);

        mockMvc.perform(doDelete("/todo/1")).andExpect(status().isNoContent());
    }

}
