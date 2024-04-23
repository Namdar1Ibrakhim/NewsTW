package com.example.newstw.service.impl;

import com.example.newstw.dto.request.NewsRequestDto;
import com.example.newstw.dto.response.NewsResponseDto;
import com.example.newstw.entity.Category;
import com.example.newstw.entity.News;
import com.example.newstw.entity.User;
import com.example.newstw.enums.Status;
import com.example.newstw.mapper.NewsMapper;
import com.example.newstw.repository.CategoryRepository;
import com.example.newstw.repository.NewsRepository;
import com.example.newstw.service.NewsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public NewsResponseDto getById(Long id) {
        News news = newsRepository.findById(id)
            .orElseThrow(() ->new EntityNotFoundException("News not fount with id: " + id));
        return newsMapper.toDto(news);
    }

    @Override
    public List<NewsResponseDto> getAll() {
        List<News> newsList = newsRepository.findAll();
        return newsMapper.toDtoList(newsList);
    }


    @Override
    public List<NewsResponseDto> filterNews(String title, String description, String imageUrl, String numberOfLikes, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<News> newsPage = newsRepository.filterNews(title, description, imageUrl, numberOfLikes, pageable);
        return newsMapper.toDtoList(newsPage);
    }

    @Override
    public void update(NewsRequestDto newsRequestDto) {
        Category category = categoryRepository.findById(newsRequestDto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + newsRequestDto.getCategoryId()));

        News news = newsRepository.findById(newsRequestDto.getId())
            .orElseThrow(() ->new EntityNotFoundException("News not fount with id: " + newsRequestDto.getId()));

        news.setTitle(newsRequestDto.getTitle());
        news.setDescription(newsRequestDto.getDescription());
        news.setImageUrl(newsRequestDto.getImageUrl());
        news.setCategory(category);
        news.setStatus(Status.WAITING);
        newsRepository.save(news);
    }

    @Override
    public void delete(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("News not fount with id: " + id));
        newsRepository.delete(news);

    }

    @Override
    public void save(NewsRequestDto newsRequestDto, User user) {
        Category category = categoryRepository.findById(newsRequestDto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + newsRequestDto.getCategoryId()));

        News news = News.builder()
                .title(newsRequestDto.getTitle())
                .description(newsRequestDto.getDescription())
                .imageUrl(newsRequestDto.getImageUrl())
                .author(user)
                .numberOfLikes(1L)
                .status(Status.WAITING)
                .category(category)
                .build();
        newsRepository.save(news);
    }

    @Override
    public List<NewsResponseDto> getByStatus(Status status) {
        List<News> newsList = newsRepository.getByStatus(status);
        return newsMapper.toDtoList(newsList);
    }

    @Override
    public void changeNewsStatus(Long newsId, Status status) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new EntityNotFoundException("News not fount with id: " + newsId));
        news.setStatus(status);
        newsRepository.save(news);
    }

    @Override
    public void likeNews(Long newsId) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new EntityNotFoundException("News not fount with id: " + newsId));
        news.setNumberOfLikes(news.getNumberOfLikes()+1);

    }


}
