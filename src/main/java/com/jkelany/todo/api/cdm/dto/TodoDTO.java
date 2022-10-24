package com.jkelany.todo.api.cdm.dto;

import com.jkelany.todo.api.validator.IpAddress;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
public class TodoDTO {
    private String id;

    @NotNull
    @Size(min = 3)
    private String title;

    @NotNull
    @Size(min = 3)
    private String desc;

    private LocalDateTime createdDate;

    @NotNull
    @IpAddress
    private String ipAddress;
}
