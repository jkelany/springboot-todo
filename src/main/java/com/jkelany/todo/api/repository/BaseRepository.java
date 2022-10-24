package com.jkelany.todo.api.repository;

import com.jkelany.todo.api.cdm.entity.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BaseRepository<T extends BaseEntity<ID>, ID> extends MongoRepository<T, ID> {

}
