package com.zuk.rest;

import com.zuk.dto.user.UserDto;
import com.zuk.dto.user.profile.UserProfileDto;
import com.zuk.model.User;
import com.zuk.model.UserProfile;
import com.zuk.service.UserProfileService;
import com.zuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {
    private final UserService userService;
    private final UserProfileService userProfileService;

    @Autowired
    public UserRestControllerV1(UserService userService, UserProfileService userProfileService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") long id){
        User user = userService.findById(id);
        //System.out.println(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "getProfile/{id}")
    public ResponseEntity<UserProfileDto> getUserProfileById(@PathVariable(name = "id") long userId){
        UserProfile userProfile = userProfileService.findById(userId);
        //System.out.println(id);

        if(userProfile == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserProfileDto result = UserProfileDto.fromUserProfile(userProfile);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}