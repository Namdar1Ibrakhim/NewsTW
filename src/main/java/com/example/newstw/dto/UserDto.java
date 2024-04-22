package com.example.newstw.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

    @NotNull(message = "First name is required")
    private String firstname;

    @NotNull(message = "Last name is required")
    private String lastname;

    @NotNull(message = "Login is required")
    private String login;

    @NotNull(message = "ImageUrl is required")
    private String imageUrl;
}
