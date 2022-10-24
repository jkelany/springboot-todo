package com.jkelany.todo.api.cdm.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
public class UserDTO {
    private String id;
    @NotEmpty
    private String name;

    @NotEmpty
    @Size(min = 5)
    private String username;

    private LocalDateTime createdDate;
}
