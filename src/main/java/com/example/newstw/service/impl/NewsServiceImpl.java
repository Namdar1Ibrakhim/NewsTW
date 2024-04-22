package com.example.newstw.service.impl;

import com.example.newstw.dto.request.NewsRequestDto;
import com.example.newstw.dto.response.NewsResponseDto;
import com.example.newstw.entity.News;
import com.example.newstw.enums.Status;
import com.example.newstw.mapper.NewsMapper;
import com.example.newstw.repository.NewsRepository;
import com.example.newstw.service.NewsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

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
    public List<NewsResponseDto> filterNews(String title, String description, String imageUrl, String numberOfLikes) {
        List<News> newsList = newsRepository.filterNews(title, description, imageUrl, numberOfLikes);
        return newsMapper.toDtoList(newsList);
    }

    @Override
    public void update(NewsRequestDto newsRequestDto) {
        News news = newsRepository.findById(newsRequestDto.getId())
            .orElseThrow(() ->new EntityNotFoundException("News not fount with id: " + newsRequestDto.getId()));
        news.setTitle(newsRequestDto.getTitle());
        news.setDescription(newsRequestDto.getDescription());
        news.setImageUrl(newsRequestDto.getImageUrl());
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
    public List<News> getByStatus(Status status) {
        List<News> newsList = newsRepository.getByStatus(status);
        return newsList;
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
