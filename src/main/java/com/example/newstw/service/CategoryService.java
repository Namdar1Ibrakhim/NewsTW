package com.example.newstw.service;


import com.example.newstw.dto.request.CategoryRequestDto;
import com.example.newstw.dto.response.CategoryResponseDto;
import com.example.newstw.dto.response.NewsResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto getCategoryById(Long id);

    List<CategoryResponseDto> getAll();

    List<NewsResponseDto> getNewsByCategoryId(Long categoryId);

    @PreAuthorize("hasAnyAuthority('MODERATOR')")
    void update(CategoryRequestDto categoryRequestDto);

    @PreAuthorize("hasAnyAuthority('MODERATOR')")
    void delete(Long categoryId);

    @PreAuthorize("hasAnyAuthority('MODERATOR')")
    void save(CategoryRequestDto categoryRequestDto);



}
