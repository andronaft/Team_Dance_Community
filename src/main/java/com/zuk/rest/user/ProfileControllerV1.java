package com.zuk.rest.user;

import com.zuk.dto.user.profile.UserProfileDto;
import com.zuk.model.User;
import com.zuk.model.UserProfile;
import com.zuk.service.UserProfileService;
import com.zuk.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/api/v1/users/profile/")
public class ProfileControllerV1 {

    private final UserService userService;
    private final UserProfileService userProfileService;

    public ProfileControllerV1(UserService userService, UserProfileService userProfileService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
    }

    @PostMapping(value = "getProfile/")
    public ResponseEntity<Map<Object, Object>> getUserProfile(Principal principal) {
        return ResponseEntity.ok(toResponse(currentProfile(principal)));
    }

    /**
     * Only the fields a user owns are taken from the request. Rating, level, status and
     * the photo (set through the upload endpoint) stay as they are.
     */
    @PostMapping(value = "updateProfile/")
    public ResponseEntity<Map<Object, Object>> updateProfile(Principal principal, @RequestBody UserProfileDto userProfileDto) {
        UserProfile userProfile = currentProfile(principal);
        userProfile.setMobile(userProfileDto.getMobile());
        userProfile.setSocial(userProfileDto.getSocial());
        userProfile.setAbout(userProfileDto.getAbout());
        return ResponseEntity.ok(toResponse(userProfileService.update(userProfile)));
    }

    private UserProfile currentProfile(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        UserProfile userProfile = userProfileService.findById(user.getId());
        if (userProfile == null) {
            throw new NoSuchElementException("Profile not found");
        }
        return userProfile;
    }

    private static Map<Object, Object> toResponse(UserProfile userProfile) {
        Map<Object, Object> response = new HashMap<>();
        response.put("user_id", userProfile.getUserId());
        response.put("mobile", userProfile.getMobile());
        response.put("social", userProfile.getSocial());
        response.put("about", userProfile.getAbout());
        response.put("img_url", userProfile.getImgUrl());
        response.put("rating", userProfile.getRating());
        response.put("level", userProfile.getLevel());
        return response;
    }
}
