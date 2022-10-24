package com.jkelany.todo.api.controller;

import com.jkelany.todo.api.cdm.dto.TodoDTO;
import com.jkelany.todo.api.cdm.entity.Todo;
import com.jkelany.todo.api.cdm.mapper.TodoMapper;
import com.jkelany.todo.api.cdm.response.PageResponse;
import com.jkelany.todo.api.exception.NotFoundException;
import com.jkelany.todo.api.exception.UnauthorizedException;
import com.jkelany.todo.api.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/todo")
@CrossOrigin
@Validated
public class TodoController extends BaseController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoMapper todoMapper;

    @GetMapping(path = "/{id}")
    public ResponseEntity<TodoDTO> get(@PathVariable String id) throws NotFoundException, UnauthorizedException {
        Todo todo = todoService.get(id);
        if (todo.getUserId() == null || !todo.getUserId().equals(authService.getCurrentUser().getId())) {
            throw new UnauthorizedException(messageSource.getMessage("exception.unauthorized_exception.message",
                    null, LocaleContextHolder.getLocale()));
        }
        return ResponseEntity.ok(todoMapper.toDto(todo));
    }

    @GetMapping
    public ResponseEntity<PageResponse> getAll(Pageable pageable) {
        Page<TodoDTO> todoDtoPage = todoService.getAll(authService.getCurrentUser().getId(), pageable)
                .map(todoMapper::toDto);
        return ResponseEntity.ok(toPageResponse(todoDtoPage));
    }

    @PostMapping
    public ResponseEntity<TodoDTO> create(@Valid @RequestBody TodoDTO todoDTO) {
        Todo todo = todoMapper.fromDto(todoDTO);
        todo.setUserId(authService.getCurrentUser().getId());
        return new ResponseEntity<>(todoMapper.toDto(todoService.insert(todo)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TodoDTO> update(@Valid @RequestBody TodoDTO todoDTO) {
        Todo todo = todoMapper.fromDto(todoDTO);
        return ResponseEntity.ok(todoMapper.toDto(todoService.update(todo)));
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) throws NotFoundException, UnauthorizedException {
        Todo todo = todoService.get(id);
        if (todo.getUserId() == null || !todo.getUserId().equals(authService.getCurrentUser().getId())) {
            throw new UnauthorizedException(messageSource.getMessage("exception.unauthorized_exception.message",
                    null, LocaleContextHolder.getLocale()));
        }

        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
