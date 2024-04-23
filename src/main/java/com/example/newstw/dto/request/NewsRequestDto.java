package com.example.newstw.dto.request;

import com.example.newstw.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsRequestDto {

    @JsonProperty("id")
    @NotNull(message = "News id should not be null")
    private Long id;

    @JsonProperty("title")
    @NotNull(message = "News title should not be null")
    private String title;

    @JsonProperty("description")
    @NotNull(message = "News description should not be null")
    private String description;

    @JsonProperty("imageUrl")
    @NotNull(message = "News imageUrl should not be null")
    private String imageUrl;

    @JsonProperty("categoryId")
    @NotNull(message = "News categoryId should not be null")
    private Long categoryId;
}
