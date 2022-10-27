package com.jkelany.todo.api.cdm.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Schema(name = "JWT Schema")
public class JwtResponse {

    private String token;

}
