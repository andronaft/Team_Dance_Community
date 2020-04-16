package com.zuk.rest.admin.users.profile;

import com.zuk.dto.user.UserDto;
import com.zuk.dto.user.profile.UserProfileDto;
import com.zuk.model.User;
import com.zuk.model.UserProfile;
import com.zuk.security.JwtAuthenticationException;
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
@RequestMapping(value = "/api/v1/admin/users/profile/")
public class AdminUserProfileControllerV1 {
    private final UserService userService;
    private final UserProfileService userProfileService;

    @Autowired
    public AdminUserProfileControllerV1(UserService userService, UserProfileService userProfileService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
    }


    @PostMapping(value = "getProfile/")
    public ResponseEntity getUserProfileById(@RequestBody UserDto userDto){


        try{
            UserProfile userProfile = userProfileService.findById(userDto.getId());
            if(userProfile == null){
                throw new BadCredentialsException("Invalid username");//return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            Map<Object, Object> response = new HashMap<>();
            response.put("user_id",userProfile.getUserId());
            response.put("mobile",userProfile.getMobile());
            response.put("social",userProfile.getSocial());
            response.put("about",userProfile.getAbout());
            response.put("img_url",userProfile.getRating());
            response.put("rating",userProfile.getRating());
            response.put("level",userProfile.getLevel());
            response.put("created",userProfile.getCreated().toLocalDateTime());
            response.put("updated",userProfile.getUpdated());
            response.put("status",userProfile.getStatus());
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (JwtAuthenticationException e){
            throw new JwtAuthenticationException("invalid token");
        } catch (AuthenticationException  e) {
            //throw  new UsernameNotFoundException("User  not found");
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping(value = "updateProfile/")
    public ResponseEntity updateUserProfileById(@RequestBody UserProfileDto userProfilDto){
        try{
            UserProfile userProfilefind = userProfileService.findById(userProfilDto.getUserId());
            UserProfile userProfilereq = userProfilDto.toUserProfile();
            userProfilereq.setCreated(userProfilefind.getCreated());
            userProfilereq.setStatus(userProfilDto.getStatus());
            UserProfile userProfile = userProfileService.update(userProfilereq);
            if(userProfile == null){
                throw new BadCredentialsException("Invalid username");//return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            Map<Object, Object> response = new HashMap<>();
            response.put("user_id",userProfile.getUserId());
            response.put("mobile",userProfile.getMobile());
            response.put("social",userProfile.getSocial());
            response.put("about",userProfile.getAbout());
            response.put("img_url",userProfile.getRating());
            response.put("rating",userProfile.getRating());
            response.put("level",userProfile.getLevel());
            response.put("created",userProfile.getCreated().toLocalDateTime());
            response.put("updated",userProfile.getUpdated());
            response.put("status",userProfile.getStatus());
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (AuthenticationException e) {

            throw new BadCredentialsException("Invalid username or password");
        }
    }
}

