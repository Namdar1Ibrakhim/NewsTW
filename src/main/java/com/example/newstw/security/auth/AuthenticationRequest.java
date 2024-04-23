package com.example.newstw.security.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    @JsonProperty("login")
    @NotNull(message = "Login is required")
    private String login;

    @JsonProperty("password")
    @NotNull(message = "Password is required")
    public String password;

}
