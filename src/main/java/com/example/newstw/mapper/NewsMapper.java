package com.example.newstw.mapper;

import com.example.newstw.dto.UserDto;
import com.example.newstw.dto.response.NewsResponseDto;
import com.example.newstw.entity.News;
import com.example.newstw.entity.User;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsMapper  extends Mappable<News, NewsResponseDto>{


}
