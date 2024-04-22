package com.example.newstw.service;


import com.example.newstw.dto.response.NewsResponseDto;

public interface CategoryService {

    CategoryResponseDto getCategoryById(Long id);

    CategoryResponseDto getAll();

    NewsResponseDto getNewsByCategoryId(Long categoryId);



}
