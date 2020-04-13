package com.zuk.rest;

import com.zuk.dto.user.AuthenticationRequestDto;
import com.zuk.dto.user.RegisterUserDto;
import com.zuk.model.User;
import com.zuk.model.UserProfile;
import com.zuk.security.JwtTokenProvider;
import com.zuk.service.UserProfileService;
import com.zuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;



@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final UserProfileService userProfileService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, UserProfileService userProfileService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.userProfileService = userProfileService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {

            String username = requestDto.getUsername();
            User user = userService.findByUsername(username);
            System.out.println(user == null);

            if (user == null) {
                System.out.println();
                return (ResponseEntity) ResponseEntity.badRequest();
                 //throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
            try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));


            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("user_id",user.getId());
            response.put("role",jwtTokenProvider.getRoleNames(user.getRoles()));
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody RegisterUserDto userDto) {
        try {
            System.out.println(userDto.toString());
            String username = userDto.getUsername();
            User user = userService.register(userDto.toUser());
            System.out.println(user.getUsername().toString());
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " can't register");
            }
            UserProfile userProfile = new UserProfile();
            userProfile.setUserId(user.getId());
            userProfileService.register(userProfile);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("user_id",user.getId());
            response.put("email",user.getEmail());
            response.put("firstName",user.getFirstName());
            response.put("lastName",user.getLastName());
            //response.put("role",userresult.getRoles().size());


            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}