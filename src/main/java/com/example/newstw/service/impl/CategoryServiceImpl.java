package com.example.newstw.service.impl;

import com.example.newstw.dto.request.CategoryRequestDto;
import com.example.newstw.dto.response.CategoryResponseDto;
import com.example.newstw.dto.response.NewsResponseDto;
import com.example.newstw.entity.Category;
import com.example.newstw.entity.News;
import com.example.newstw.mapper.CategoryMapper;
import com.example.newstw.mapper.NewsMapper;
import com.example.newstw.repository.CategoryRepository;
import com.example.newstw.repository.NewsRepository;
import com.example.newstw.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("Category not fount with id: " + id));
        return categoryMapper.toDto(category);

    }

    @Override
    public List<CategoryResponseDto> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryMapper.toDtoList(categoryList);
    }

    @Override
    public NewsResponseDto getNewsByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->new EntityNotFoundException("Category not fount with id: " + categoryId));

        News news = newsRepository.findByCategory(category)
                .orElseThrow(() -> new EntityNotFoundException("News not found with category by id: " + categoryId));
        return newsMapper.toDto(news);
    }

    @Override
    public void update(CategoryRequestDto categoryRequestDto) {
        Category category = categoryRepository.findById(categoryRequestDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + categoryRequestDto.getId()));
        if(categoryRequestDto.getName()!=null)
            category.setName(categoryRequestDto.getName());
        if(categoryRequestDto.getDescription()!=null)
            category.setDescription(categoryRequestDto.getDescription());
        categoryRepository.save(category);
    }

    @Override
    public void delete(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + categoryId));
        categoryRepository.delete(category);
    }

    @Override
    public void save(CategoryRequestDto categoryRequestDto) {
        Category category = Category.builder()
                .name(categoryRequestDto.getName())
                .description(categoryRequestDto.getDescription())
                .build();
        categoryRepository.save(category);
    }
}
