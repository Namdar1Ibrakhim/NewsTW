package com.example.newstw.controllers;

import com.example.newstw.dto.request.CommentsRequestDto;
import com.example.newstw.dto.response.CategoryResponseDto;
import com.example.newstw.dto.response.CommentsResponseDto;
import com.example.newstw.entity.User;
import com.example.newstw.service.CommentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/comments")
public class CommentsController {

    private final CommentsService commentsService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<CommentsResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(commentsService.getById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CommentsResponseDto>> getAll(){
        return ResponseEntity.ok(commentsService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid CommentsRequestDto commentsRequestDto, @AuthenticationPrincipal User user){
        commentsService.add(commentsRequestDto, user);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        commentsService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
