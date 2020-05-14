package com.zuk.rest;

import com.zuk.dto.award.AwardDto;
import com.zuk.dto.user.UserDto;
import com.zuk.dto.user.profile.UserProfileDto;
import com.zuk.model.Role;
import com.zuk.model.User;
import com.zuk.model.UserProfile;
import com.zuk.repository.RoleRepository;
import com.zuk.security.JwtTokenProvider;
import com.zuk.service.AwardService;
import com.zuk.service.UserProfileService;
import com.zuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/userPublic/")
public class UserPublicControllerV1 {

    private final UserService userService;
    private final UserProfileService userProfileService;

    private final AwardService awardService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserPublicControllerV1(UserService userService, UserProfileService userProfileService, RoleRepository roleRepository, AwardService awardService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.userProfileService = userProfileService;

        this.awardService = awardService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("findAllPublicByUserId/")
    public ResponseEntity findAllPublicByUserId(@RequestParam Long id ){

        User user = userService.findById(id);
        UserProfile userProfile = userProfileService.findById(id);
        Map<Object, Object> response = new HashMap<>();

        if(!jwtTokenProvider.getRoleNames(user.getRoles()).contains("ROLE_ADMIN")){
            response.put("role",jwtTokenProvider.getRoleNames(user.getRoles()));
        }
        response.put("user", UserDto.fromTrainerToPublicDto(user));
        response.put("profile", UserProfileDto.fromTrainerPublicProfile(userProfile));
        response.put("award",AwardDto.fromArrayAward(awardService.findByUser(user)));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("findById/")
    public ResponseEntity findById(@RequestParam Long id ){
        User user = userService.findById(id);
        UserProfile userProfile = userProfileService.findById(id);
        Map<Object, Object> response = new HashMap<>();
        response.put("user", UserDto.fromTrainerToPublicDto(user));
        response.put("profile", UserProfileDto.fromTrainerPublicProfile(userProfile));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("findAwardByUser/")
    public ResponseEntity findAwardByUser(@RequestParam Long id ){
        User user = userService.findById(id);
        return new ResponseEntity<>(AwardDto.fromArrayAward(awardService.findByUser(user)), HttpStatus.OK);
    }


}
