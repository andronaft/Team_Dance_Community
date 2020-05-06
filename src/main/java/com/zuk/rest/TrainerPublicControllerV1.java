package com.zuk.rest;

import com.zuk.dto.award.AwardDto;
import com.zuk.dto.user.UserDto;
import com.zuk.dto.user.profile.UserProfileDto;
import com.zuk.model.User;
import com.zuk.model.UserProfile;
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
public class TrainerPublicControllerV1 {

    private final UserService userService;
    private final UserProfileService userProfileService;
    private final AwardService awardService;

    @Autowired
    public TrainerPublicControllerV1(UserService userService, UserProfileService userProfileService, AwardService awardService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.awardService = awardService;
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
    public ResponseEntity findAwardByUser(@RequestParam Long id ){//TODO add to katalon
        User user = userService.findById(id);
        return new ResponseEntity<>(AwardDto.fromArrayAward(awardService.findByUser(user)), HttpStatus.OK);
    }


}
