package com.example.newstw.mapper;

import com.example.newstw.dto.UserDto;
import com.example.newstw.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto>{
    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
