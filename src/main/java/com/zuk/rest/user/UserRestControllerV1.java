package com.zuk.rest.user;

import com.zuk.dto.user.PasswordDto;
import com.zuk.dto.user.UserDto;
import com.zuk.dto.user.profile.UserProfileDto;
import com.zuk.model.User;
import com.zuk.model.UserProfile;
import com.zuk.security.JwtTokenProvider;
import com.zuk.service.UserProfileService;
import com.zuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {
    private final UserService userService;
    private final UserProfileService userProfileService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserRestControllerV1(UserService userService, UserProfileService userProfileService, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
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

    @GetMapping(value = "getAllUserInfo")
    public ResponseEntity getAllUserInfo(@RequestHeader("Authorization") String token){
        User user = userService.findByUsername(jwtTokenProvider.getUsername(token.substring(7)));
        UserProfile userProfile = userProfileService.findById(user.getId());
        Map<Object, Object> response = new HashMap<>();
        response.put("user", UserDto.fromUser(user));
        response.put("profile",UserProfileDto.fromUserProfile(userProfile));
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "updatePassword")
    public ResponseEntity updatePassword(@RequestBody PasswordDto passwordDto,@RequestHeader("Authorization") String token){
        User user = userService.findByUsername(jwtTokenProvider.getUsername(token.substring(7)));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),passwordDto.getOldPassword()));
        System.out.println(user.getPassword() + "User find by token");
        System.out.println(passwordDto);
        User userUpdate = userService.updatePassword(passwordDto.getNewPassword(),user.getId());
        System.out.println(userUpdate.getPassword() + " UserUpdate");
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "password changed successfully");
        return ResponseEntity.ok(response);
    }



}