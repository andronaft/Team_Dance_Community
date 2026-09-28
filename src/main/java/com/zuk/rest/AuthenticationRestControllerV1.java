package com.zuk.rest;

import com.zuk.dto.auth.AuthenticationRequestDto;
import com.zuk.dto.auth.RegisterUserDto;
import com.zuk.dto.user.check.CheckEmailDto;
import com.zuk.dto.user.check.CheckMobileDto;
import com.zuk.dto.user.check.CheckUsernameDto;
import com.zuk.model.User;
import com.zuk.model.UserProfile;
import com.zuk.security.JwtTokenProvider;
import com.zuk.service.UserProfileService;
import com.zuk.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    private static final int MIN_PASSWORD_LENGTH = 8;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final UserProfileService userProfileService;

    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, UserProfileService userProfileService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.userProfileService = userProfileService;
    }

    /**
     * Unknown user, wrong password and a not yet activated account all fail with the same
     * 401 answer (handled in RestExceptionHandler), so the endpoint doesn't reveal which usernames exist.
     */
    @PostMapping("login")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthenticationRequestDto requestDto) {
        String username = requestDto.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));

        User user = userService.findByUsername(username);
        String token = jwtTokenProvider.createToken(username, user.getRoles());

        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("user_id", user.getId());
        response.put("role", jwtTokenProvider.getRoleNames(user.getRoles()));
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    /**
     * New accounts start as NOT_ACTIVE and an admin activates them, so registration doesn't log the user in:
     * it used to try, which always failed on the inactive account after the user had already been saved.
     */
    @PostMapping("register")
    @Transactional
    public ResponseEntity<Map<Object, Object>> register(@RequestBody RegisterUserDto userDto) {
        if (!StringUtils.hasText(userDto.getUsername()) || !StringUtils.hasText(userDto.getEmail())) {
            throw new IllegalArgumentException("Username and email are required");
        }
        if (userDto.getPassword() == null || userDto.getPassword().length() < MIN_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("Password must be at least " + MIN_PASSWORD_LENGTH + " characters long");
        }
        if (userService.checkUsername(userDto.getUsername())) {
            throw new IllegalArgumentException("Username " + userDto.getUsername() + " is not available");
        }
        if (userService.checkEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email " + userDto.getEmail() + " is already registered");
        }

        User user = userService.register(userDto.toUser());

        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(user.getId());
        userProfileService.register(userProfile);

        Map<Object, Object> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("user_id", user.getId());
        response.put("email", user.getEmail());
        response.put("firstName", user.getFirstName());
        response.put("lastName", user.getLastName());
        response.put("role", jwtTokenProvider.getRoleNames(user.getRoles()));
        response.put("status", user.getStatus());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("checkUsername")
    public ResponseEntity<Map<Object, Object>> checkUsername(@RequestBody CheckUsernameDto checkUsernameDto){
        Map<Object, Object> response = new HashMap<>();
        response.put("isExist",userService.checkUsername(checkUsernameDto.getUsername()));
        return ResponseEntity.ok(response);
    }

    @PostMapping("checkEmail")
    public ResponseEntity<Map<Object, Object>> checkEmail(@RequestBody CheckEmailDto checkEmailDto){
        Map<Object, Object> response = new HashMap<>();
        response.put("isExist",userService.checkEmail(checkEmailDto.getEmail()));
        return ResponseEntity.ok(response);
    }

    @PostMapping("checkMobile")
    public ResponseEntity<Map<Object, Object>> checkMobile(@RequestBody CheckMobileDto checkMobileDto){
        Map<Object, Object> response = new HashMap<>();
        response.put("isExist",userProfileService.checkMobile(checkMobileDto.getMobile()));
        return ResponseEntity.ok(response);
    }
}
