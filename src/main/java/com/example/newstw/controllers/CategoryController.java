package com.example.newstw.controllers;

import com.example.newstw.dto.request.CategoryRequestDto;
import com.example.newstw.dto.response.CategoryResponseDto;
import com.example.newstw.dto.response.NewsResponseDto;
import com.example.newstw.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/category")
public class CategoryController{

    private final CategoryService categoryService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryResponseDto>> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/getNewsByCategoryId{id}")
    public ResponseEntity<List<NewsResponseDto>> getNewsByCategoryId(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getNewsByCategoryId(id));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody @Valid CategoryRequestDto categoryRequestDto){
        categoryService.update(categoryRequestDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        categoryService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody @Valid CategoryRequestDto categoryRequestDto){
        categoryService.save(categoryRequestDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
