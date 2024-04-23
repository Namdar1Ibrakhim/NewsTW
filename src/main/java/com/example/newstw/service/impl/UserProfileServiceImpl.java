package com.example.newstw.service.impl;


import com.example.newstw.dto.request.PasswordEditRequest;
import com.example.newstw.dto.response.UserDto;
import com.example.newstw.entity.User;
import com.example.newstw.exception.PasswordMismatchException;
import com.example.newstw.exception.UserNotFoundException;
import com.example.newstw.mapper.UserMapper;
import com.example.newstw.repository.UserRepository;
import com.example.newstw.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper mapper;


    @Override
    public UserDto getCurrentUserDetails(User user) {
        return mapper.toDto(user);

    }
    @Override
    public UserDto getUserDetailsById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return mapper.toDto(user);
    }

    @Override
    @SneakyThrows
    public UserDto getUserDetailsByLogin(String email){
        User user = userRepository.findByLogin(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return mapper.toDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUserProfile(UserDto userDto, User user) {
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setLogin(userDto.getLogin());
        user.setImageUrl(userDto.getImageUrl());
        userRepository.save(user);
        return mapper.toDto(user);
    }

    @Override
    @Transactional
    public void deleteUserProfile(User user) {
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void editPassword(PasswordEditRequest request, User user) throws PasswordMismatchException {
        if(!request.getRePassword().equals(request.getNewPassword())) throw new PasswordMismatchException("Password mismatch");
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
