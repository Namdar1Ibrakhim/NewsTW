package com.example.newstw.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDto {

    @JsonProperty("id")
    @NotNull(message = "Category id should not be null")
    private Long id;

    @JsonProperty("name")
    @NotNull(message = "Category name should not be null")
    private String name;

    @JsonProperty("description")
    @NotNull(message = "Category description should not be null")
    private String description;
}
