package com.zuk.rest.user;


import com.zuk.dto.user.UserDto;
import com.zuk.dto.user.check.CheckMobileDto;
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
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/users/profile/")
public class ProfileControllerV1 {

    private final UserService userService;
    private final UserProfileService userProfileService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public ProfileControllerV1(UserService userService, UserProfileService userProfileService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "getProfile/")
    public ResponseEntity getUserProfileById( @RequestHeader("Authorization") String token){

        try {

                User user = userService.findByUsername(jwtTokenProvider.getUsername(token.substring(7)));

                UserProfile userProfile = userProfileService.findById(user.getId());
                if (userProfile == null) {
                    throw new BadCredentialsException("Invalid username");//return new ResponseEntity(HttpStatus.NO_CONTENT);
                }
                System.out.println("3");
                Map<Object, Object> response = new HashMap<>();
                response.put("user_id", userProfile.getUserId());
                response.put("mobile", userProfile.getMobile());
                response.put("social", userProfile.getSocial());
                response.put("about", userProfile.getAbout());
                response.put("img_url", userProfile.getRating());
                response.put("rating", userProfile.getRating());
                response.put("level", userProfile.getLevel());
                return new ResponseEntity(response, HttpStatus.OK);


        } catch (AuthenticationException e) {
            //throw  new UsernameNotFoundException("User  not found");
            throw new BadCredentialsException("Invalid username or password");
        }

    }
    @PostMapping(value = "updateProfile/")
    public ResponseEntity updateProfile(@RequestHeader("Authorization") String token,@RequestBody UserProfileDto userProfileDto){

        try {

                User user = userService.findByUsername(jwtTokenProvider.getUsername(token.substring(7)));
                UserProfile userProfile = userProfileDto.toUserProfile();
                userProfile.setUserId(user.getId());
                userProfile.setCreated(user.getCreated());
                userProfile.setStatus(user.getStatus());
                UserProfile userProfileUpdate = userProfileService.update(userProfile);
                if (userProfileUpdate == null) {
                    throw new BadCredentialsException("Invalid username");//return new ResponseEntity(HttpStatus.NO_CONTENT);
                }
                System.out.println("3");
                Map<Object, Object> response = new HashMap<>();
                response.put("user_id", userProfileUpdate.getUserId());
                response.put("mobile", userProfileUpdate.getMobile());
                response.put("social", userProfileUpdate.getSocial());
                response.put("about", userProfileUpdate.getAbout());
                response.put("img_url", userProfileUpdate.getRating());
                response.put("rating", userProfileUpdate.getRating());
                response.put("level", userProfileUpdate.getLevel());
                return new ResponseEntity(response, HttpStatus.OK);


        }catch (AuthenticationException e) {
            //throw  new UsernameNotFoundException("User  not found");
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}
