package com.example.newstw.mapper;

import com.example.newstw.dto.response.CategoryResponseDto;
import com.example.newstw.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends Mappable<Category, CategoryResponseDto>{
}
