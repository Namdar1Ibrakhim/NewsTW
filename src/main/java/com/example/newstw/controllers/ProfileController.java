package com.example.newstw.controllers;


import com.example.newstw.dto.request.PasswordEditRequest;
import com.example.newstw.dto.UserDto;
import com.example.newstw.entity.User;
import com.example.newstw.exception.PasswordMismatchException;
import com.example.newstw.exception.UserNotFoundException;
import com.example.newstw.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/profile")
public class ProfileController {

    private final UserProfileService userProfileService;

    @RequestMapping("/{user_id}")
    @Operation(summary = "Get UserDto by id")
    public ResponseEntity<UserDto> getUserProfileById(@PathVariable Long user_id) throws UserNotFoundException {
        UserDto userProfileDto = userProfileService.getUserDetailsById(user_id);
        return ResponseEntity.ok(userProfileDto);
    }

    @Operation(summary = "Get UserDto by login")
    @RequestMapping("/byLogin/{login}")
    public ResponseEntity<UserDto> getUserProfileByLogin(@PathVariable String login){
        UserDto userDto = userProfileService.getUserDetailsByLogin(login);
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "Get UserDto for current user")
    @GetMapping("")
    public ResponseEntity<UserDto> myProfile(@AuthenticationPrincipal User user){
        UserDto userProfileDto = userProfileService.getCurrentUserDetails(user);
        return ResponseEntity.ok(userProfileDto);
    }

    @Operation(summary = "Update user data")
    @PostMapping("/update")
    public ResponseEntity updateUserProfile(@RequestBody @Valid UserDto userDto, @AuthenticationPrincipal User user){
        userProfileService.updateUserProfile(userDto, user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete user data")
    @DeleteMapping("/delete")
    public ResponseEntity deleteUserProfile(@AuthenticationPrincipal User user){
        userProfileService.deleteUserProfile(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Edit password for user")
    @PostMapping("/editPassword")
    public ResponseEntity editPassword(@RequestBody @Valid PasswordEditRequest request, @AuthenticationPrincipal User user) throws PasswordMismatchException {
        userProfileService.editPassword(request, user);
        return new ResponseEntity<>(HttpStatus.OK);


    }


}
