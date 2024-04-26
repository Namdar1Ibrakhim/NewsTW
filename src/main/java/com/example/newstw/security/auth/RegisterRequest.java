package com.example.newstw.security.auth;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private String imageUrl;
}
