package com.example.newstw.dto.response;

import com.example.newstw.entity.User;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
public class NewsResponseDto {

    private Long id;

    private String title;

    private String description;

    @OneToOne
    private User author;

    private String imageUrl;

    private String numberOfLikes;
}
