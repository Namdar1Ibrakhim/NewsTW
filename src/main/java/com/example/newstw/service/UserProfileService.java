package com.example.newstw.service;


import com.example.newstw.dto.request.PasswordEditRequest;
import com.example.newstw.dto.UserDto;
import com.example.newstw.entity.User;
import com.example.newstw.exception.PasswordMismatchException;
import com.example.newstw.exception.UserNotFoundException;

public interface UserProfileService {
    UserDto getCurrentUserDetails(User user);

    UserDto getUserDetailsById(Long id) throws UserNotFoundException;

    UserDto updateUserProfile(UserDto userDto, User user);

    void deleteUserProfile(User user);

    void editPassword(PasswordEditRequest request, User user) throws PasswordMismatchException;

    UserDto getUserDetailsByLogin(String login);
}
