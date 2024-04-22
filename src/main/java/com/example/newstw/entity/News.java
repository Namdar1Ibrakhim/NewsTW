package com.example.newstw.entity;

import com.example.newstw.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "news")
public class News extends BaseEntity{

    private String title;

    private String description;

    @OneToOne
    private User author;

    private String imageUrl;

    private Long numberOfLikes = 0L;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Category category;

    @OneToMany
    private List<Comments> commentsList;




}
