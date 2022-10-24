package com.jkelany.todo.api.cdm.entity;

import lombok.*;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document
@EnableMongoAuditing
public class Todo extends BaseEntity<String> {

    @NotNull
    @Size(min = 3)
    @Indexed
    private String title;

    @NotNull
    @Size(min = 3)
    private String desc;

    private String userId;

}
