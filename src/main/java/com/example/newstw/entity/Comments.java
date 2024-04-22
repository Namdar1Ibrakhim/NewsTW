package com.example.newstw.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comments extends BaseEntity{

    private String text;

    @ManyToOne
    private User author;

    @ManyToOne
    private News news;
}
