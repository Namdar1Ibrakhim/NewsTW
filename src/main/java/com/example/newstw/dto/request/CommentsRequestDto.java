package com.example.newstw.dto.request;

import com.example.newstw.entity.News;
import com.example.newstw.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsRequestDto {

    @JsonProperty("text")
    @NotNull(message = "Comments text should not be null")
    private String text;

    @JsonProperty("newsId")
    @NotNull(message = "Comments for newsId should not be null")
    private Long newsId;

}
