package com.example.newstw.mapper;

import com.example.newstw.dto.response.CommentsResponseDto;
import com.example.newstw.entity.Comments;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentsMapper extends Mappable<Comments, CommentsResponseDto>{
}
