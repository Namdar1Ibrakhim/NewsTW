package com.example.newstw.service.impl;

import com.example.newstw.dto.request.CommentsRequestDto;
import com.example.newstw.dto.response.CommentsResponseDto;
import com.example.newstw.dto.response.NewsResponseDto;
import com.example.newstw.entity.Comments;
import com.example.newstw.entity.News;
import com.example.newstw.entity.User;
import com.example.newstw.mapper.CommentsMapper;
import com.example.newstw.repository.CommentsRepository;
import com.example.newstw.repository.NewsRepository;
import com.example.newstw.service.CommentsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;
    private final CommentsMapper commentsMapper;
    private final NewsRepository newsRepository;

    @Override
    public void add(CommentsRequestDto commentsRequestDto, User user) {
        News news = newsRepository.findById(commentsRequestDto.getNewsId())
                .orElseThrow(() ->new EntityNotFoundException("News not fount with id: " + commentsRequestDto.getNewsId()));

        Comments comments = Comments.builder()
                .author(user)
                .text(commentsRequestDto.getText())
                .news(news)
                .build();
        commentsRepository.save(comments);
    }

    @Override
    public void delete(Long id) {
        Comments comments = commentsRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("Comments not fount with id: " + id));
        commentsRepository.delete(comments);
    }

    @Override
    public CommentsResponseDto getById(Long id) {
        Comments comments = commentsRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("Comments not fount with id: " + id));
        return commentsMapper.toDto(comments);
    }

    @Override
    public List<CommentsResponseDto> getAll() {
        List<Comments> commentsList = commentsRepository.findAll();
        return commentsMapper.toDtoList(commentsList);
    }
}
