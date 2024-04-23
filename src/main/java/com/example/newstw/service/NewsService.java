package com.example.newstw.service;

import com.example.newstw.dto.request.NewsRequestDto;
import com.example.newstw.dto.response.NewsResponseDto;
import com.example.newstw.entity.News;
import com.example.newstw.entity.User;
import com.example.newstw.enums.Status;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface NewsService {

    NewsResponseDto getById(Long id);

    List<NewsResponseDto> getAll();

    List<NewsResponseDto> filterNews(String title, String description, String imageUrl, String numberOfLikes, int page, int size);

    @PreAuthorize("hasAnyAuthority('MODERATOR')")
    void update(NewsRequestDto newsRequestDto);

    @PreAuthorize("hasAnyAuthority('MODERATOR')")
    void delete(Long id);

    void save(NewsRequestDto newsRequestDto, User user);

    @PreAuthorize("hasAnyAuthority('MODERATOR')")
    List<NewsResponseDto> getByStatus(Status status);

    @PreAuthorize("hasAnyAuthority('MODERATOR')")
    void changeNewsStatus(Long newsId, Status status);

    void likeNews(Long newsId);
}
