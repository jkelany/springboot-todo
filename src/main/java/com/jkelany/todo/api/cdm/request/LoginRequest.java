package com.jkelany.todo.api.cdm.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @NotNull(message = "Username should not be null")
    @Size(min = 3, message = "Username length should be at least 3 characters")
    private String username;

    @NotNull(message = "Password should not be null")
    @Size(min = 3, message = "Password length should be at least 3 characters")
    private String password;

}
