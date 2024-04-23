package com.example.newstw.service;

import com.example.newstw.dto.request.CommentsRequestDto;
import com.example.newstw.dto.response.CommentsResponseDto;
import com.example.newstw.dto.response.NewsResponseDto;
import com.example.newstw.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface CommentsService {

    CommentsResponseDto getById(Long id);

    List<CommentsResponseDto> getAll();

    void add(CommentsRequestDto commentsRequestDto, User user);

    @PreAuthorize("hasAnyAuthority('MODERATOR')")
    void delete(Long id);

}
