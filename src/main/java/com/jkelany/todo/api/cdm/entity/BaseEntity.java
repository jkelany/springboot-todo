package com.jkelany.todo.api.cdm.entity;

import com.jkelany.todo.api.validator.IpAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntity<ID> {

    @Id
    private ID id;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @NotNull
    @IpAddress
    private String ipAddress;
}
