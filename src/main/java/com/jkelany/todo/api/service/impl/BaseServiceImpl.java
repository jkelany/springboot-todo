package com.jkelany.todo.api.service.impl;

import com.jkelany.todo.api.cdm.entity.BaseEntity;
import com.jkelany.todo.api.exception.NotFoundException;
import com.jkelany.todo.api.repository.BaseRepository;
import com.jkelany.todo.api.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<T extends BaseEntity<ID>, ID> implements BaseService<T, ID> {

    @Autowired
    protected BaseRepository<T, ID> baseRepository;

    @Autowired
    protected MessageSource messageSource;

    @Override
    public T get(ID id) throws NotFoundException {
        Optional<T> entityOptional = baseRepository.findById(id);
        if (entityOptional.isEmpty()) {
            throw new NotFoundException(
                    messageSource.getMessage("exception.not_found_exception.message",
                            null, LocaleContextHolder.getLocale()));
        }
        return entityOptional.get();
    }

    @Override
    public List<T> getAll() {
        return baseRepository.findAll();
    }

    @Override
    public T insert(T item) {
        return baseRepository.insert(item);
    }

    @Override
    public T update(T item) {
        return baseRepository.save(item);
    }

    @Override
    public void delete(ID id) throws NotFoundException {
        if (baseRepository.findById(id).isEmpty()) {
            throw new NotFoundException(
                    messageSource.getMessage("exception.not_found_exception.message",
                            null, LocaleContextHolder.getLocale()));
        }
        baseRepository.deleteById(id);
    }

}
