package com.example.newstw.dto.request;

import com.example.newstw.entity.User;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsRequestDto {

    private Long id;

    private String title;

    private String description;

    private String imageUrl;

    private String numberOfLikes;

    private Long categoryId;
}
