package com.example.newstw.service;


import com.example.newstw.dto.request.CategoryRequestDto;
import com.example.newstw.dto.response.CategoryResponseDto;
import com.example.newstw.dto.response.NewsResponseDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto getCategoryById(Long id);

    List<CategoryResponseDto> getAll();

    NewsResponseDto getNewsByCategoryId(Long categoryId);

    void update(CategoryRequestDto categoryRequestDto);

    void delete(Long categoryId);

    void save(CategoryRequestDto categoryRequestDto);



}
