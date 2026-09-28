package com.zuk.rest.user;

import com.zuk.dto.user.PasswordDto;
import com.zuk.dto.user.UserDto;
import com.zuk.dto.user.profile.UserProfileDto;
import com.zuk.model.User;
import com.zuk.model.UserProfile;
import com.zuk.service.UserProfileService;
import com.zuk.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {

    private static final int MIN_PASSWORD_LENGTH = 8;

    private final UserService userService;
    private final UserProfileService userProfileService;
    private final AuthenticationManager authenticationManager;

    public UserRestControllerV1(UserService userService, UserProfileService userProfileService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") long id){
        User user = userService.findById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(UserDto.fromUser(user), HttpStatus.OK);
    }

    @GetMapping(value = "getAllUserInfo")
    public ResponseEntity<Map<Object, Object>> getAllUserInfo(Principal principal){
        User user = userService.findByUsername(principal.getName());
        UserProfile userProfile = userProfileService.findById(user.getId());
        Map<Object, Object> response = new HashMap<>();
        response.put("user", UserDto.fromUser(user));
        response.put("profile", userProfile == null ? null : UserProfileDto.fromUserProfile(userProfile));
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "updatePassword")
    public ResponseEntity<Map<Object, Object>> updatePassword(@RequestBody PasswordDto passwordDto, Principal principal){
        if (passwordDto.getNewPassword() == null || passwordDto.getNewPassword().length() < MIN_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("Password must be at least " + MIN_PASSWORD_LENGTH + " characters long");
        }
        User user = userService.findByUsername(principal.getName());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), passwordDto.getOldPassword()));
        userService.updatePassword(passwordDto.getNewPassword(), user.getId());
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "password changed successfully");
        return ResponseEntity.ok(response);
    }
}
