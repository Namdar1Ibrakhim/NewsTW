package com.example.newstw.service;

import com.example.newstw.dto.request.NewsRequestDto;
import com.example.newstw.dto.response.NewsResponseDto;
import com.example.newstw.entity.News;
import com.example.newstw.enums.Status;

import java.util.List;

public interface NewsService {

    NewsResponseDto getById(Long id);

    List<NewsResponseDto> getAll();

    List<NewsResponseDto> filterNews(String title, String description, String imageUrl, String numberOfLikes);

    void update(NewsRequestDto newsRequestDto);

    void delete(Long id);

    List<News> getByStatus(Status status);

    void changeNewsStatus(Long newsId, Status status);

    void likeNews(Long newsId);
}
