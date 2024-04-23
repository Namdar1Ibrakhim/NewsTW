package com.example.newstw.service.impl;

import com.example.newstw.entity.Comments;
import com.example.newstw.mapper.CommentsMapper;
import com.example.newstw.repository.CommentsRepository;
import com.example.newstw.service.CommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;
    private final CommentsMapper commentsMapper;
}
