package com.example.newstw.mapper;

import com.example.newstw.dto.response.NewsResponseDto;
import com.example.newstw.entity.News;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper extends Mappable<News, NewsResponseDto>{


}
