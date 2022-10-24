package com.jkelany.todo.api.service;

import com.jkelany.todo.api.ApplicationTest;
import com.jkelany.todo.api.cdm.entity.Todo;
import com.jkelany.todo.api.exception.NotFoundException;
import com.jkelany.todo.api.repository.TodoRepository;
import com.jkelany.todo.api.service.impl.TodoServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class TodoServiceTest extends ApplicationTest {

    @InjectMocks
    private TodoServiceImpl todoService;

    @Mock
    private TodoRepository todoRepository;

    @Test
    void whenGetAll_ReturnTodoList() {
        Todo todo1 = new Todo("Todo1", "Todo1 Desc", "1");
        Todo todo2 = new Todo("Todo2", "Todo2 Desc", "1");

        List<Todo> list = Arrays.asList(todo1, todo2);

        when(todoRepository.findAll()).thenReturn(list);

        List<Todo> getAllList = todoService.getAll();

        assertEquals(2, getAllList.size());

        assertArrayEquals(getAllList.toArray(), list.toArray());

        verify(todoRepository, times(1)).findAll();
    }

    @Test
    void whenGetById_TodoShouldFound() {
        Todo todo = new Todo("Todo1", "Todo1 Desc", "1");

        when(todoRepository.findById(anyString())).thenReturn(Optional.ofNullable(todo));

        assertEquals("Todo1", todoService.get(anyString()).getTitle());

        verify(todoRepository, times(1)).findById(anyString());
    }

    @Test
    void whenGetByInvalidId_TodoShouldNotFound() {
        when(todoRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> todoService.get(anyString()));
    }
}
