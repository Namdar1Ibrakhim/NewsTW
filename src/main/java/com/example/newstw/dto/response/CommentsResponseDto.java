package com.example.newstw.dto.response;

import com.example.newstw.entity.News;
import com.example.newstw.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CommentsResponseDto {

    private Long id;

    private String text;

    private User author;
}
