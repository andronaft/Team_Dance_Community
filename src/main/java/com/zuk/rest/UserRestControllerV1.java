package com.zuk.rest;

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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {
    private final UserService userService;
    private final UserProfileService userProfileService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserRestControllerV1(UserService userService, UserProfileService userProfileService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.jwtTokenProvider = jwtTokenProvider;
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

    @PostMapping(value = "getProfile/")
    public ResponseEntity getUserProfileById(@RequestBody UserDto userDto,@RequestHeader("Authorization") String token){


        System.out.println(jwtTokenProvider.getUsername(token.substring(7)));
        if(jwtTokenProvider.getUsername(token.substring(7)).equals(userDto.getUsername())){
            System.out.println("1");
            User user = userService.findByUsername(userDto.getUsername());
            System.out.println("2");
            UserProfile userProfile = userProfileService.findById(user.getId());
            if(userProfile == null){
                throw new BadCredentialsException("Invalid username");//return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            System.out.println("3");
            Map<Object, Object> response = new HashMap<>();
            response.put("user_id",userProfile.getUserId());
            response.put("mobile",userProfile.getMobile());
            response.put("social",userProfile.getSocial());
            response.put("about",userProfile.getAbout());
            response.put("img_url",userProfile.getRating());
            response.put("rating",userProfile.getRating());
            response.put("level",userProfile.getLevel());
            return new ResponseEntity(response, HttpStatus.OK);
        }

        throw new BadCredentialsException("Invalid username");//return new ResponseEntity(HttpStatus.NO_CONTENT);


    }
    @PostMapping(value = "updateProfile/")
    public ResponseEntity updateProfile(@RequestBody UserDto userDto,@RequestHeader("Authorization") String token,@RequestBody UserProfileDto userProfileDto){


        System.out.println(jwtTokenProvider.getUsername(token.substring(7)));
        if(jwtTokenProvider.getUsername(token.substring(7)).equals(userDto.getUsername())){
            System.out.println("1");
            User user = userService.findByUsername(userDto.getUsername());
            System.out.println("2");
            UserProfile userProfile = userProfileService.findById(user.getId());
            if(userProfile == null){
                throw new BadCredentialsException("Invalid username");//return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            System.out.println("3");
            Map<Object, Object> response = new HashMap<>();
            response.put("user_id",userProfile.getUserId());
            response.put("mobile",userProfile.getMobile());
            response.put("social",userProfile.getSocial());
            response.put("about",userProfile.getAbout());
            response.put("img_url",userProfile.getRating());
            response.put("rating",userProfile.getRating());
            response.put("level",userProfile.getLevel());
            return new ResponseEntity(response, HttpStatus.OK);
        }

        throw new BadCredentialsException("Invalid username");//return new ResponseEntity(HttpStatus.NO_CONTENT);


    }
}