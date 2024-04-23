package com.example.newstw.controllers;

import com.example.newstw.dto.request.NewsRequestDto;
import com.example.newstw.dto.response.NewsResponseDto;
import com.example.newstw.entity.News;
import com.example.newstw.entity.User;
import com.example.newstw.enums.Status;
import com.example.newstw.service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/news")
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/news")
    public ResponseEntity<List<NewsResponseDto>> filterNews(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String imageUrl,
            @RequestParam(required = false) String numberOfLikes,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(newsService.filterNews(title, description, imageUrl, numberOfLikes, page, size));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<NewsResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.getById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<NewsResponseDto>> getAll() {
        return ResponseEntity.ok(newsService.getAll());
    }

    @GetMapping("/getByStatus")
    public ResponseEntity<List<NewsResponseDto>> getByStatus(@RequestParam Status status) {
        return ResponseEntity.ok(newsService.getByStatus(status));
    }

    @PatchMapping("/likeByNews/{id}")
    public ResponseEntity likeNews(@PathVariable Long id) {
        newsService.likeNews(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody @Valid NewsRequestDto newsRequestDto, @AuthenticationPrincipal User user) {
        newsService.save(newsRequestDto, user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/changeStatus")
    public ResponseEntity changeNewsStatus(@PathVariable Long newsId, @RequestParam Status status) {
        newsService.changeNewsStatus(newsId, status);
        return new ResponseEntity(HttpStatus.OK);
    }

}
