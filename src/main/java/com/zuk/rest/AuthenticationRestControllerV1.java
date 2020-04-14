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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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
        try {
            String username = requestDto.getUsername();
            User user = userService.findByUsername(username);
            System.out.println(user == null);

            if (user == null) {
                System.out.println("return");
                //return (ResponseEntity) ResponseEntity.badRequest();
               //return ResponseEntity.status(406).build();
                throw new BadCredentialsException("Invalid username or password");
               //throw new BadCredentialsException
               //UsernameNotFoundException("User with username: " + username + " not found");
            }

                System.out.println("try");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));


            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("user_id",user.getId());
            response.put("role",jwtTokenProvider.getRoleNames(user.getRoles()));
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
               // throw  new UsernameNotFoundException("User  not found");
                //
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody RegisterUserDto userDto) {
        try {
            System.out.println(userDto.toString());
            String username = userDto.getUsername();
            User checkUser = userService.findByUsername(userDto.getUsername());

            if(checkUser!=null){
                throw new UsernameNotFoundException("Username: " + username + " is not available");
            }
            User user = userService.register(userDto.toUser());

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " can't register");
            }

            UserProfile userProfile = new UserProfile();
            userProfile.setUserId(user.getId());
            userProfileService.register(userProfile);

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, userDto.getPassword()));


            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("user_id",user.getId());
            response.put("email",user.getEmail());
            response.put("firstName",user.getFirstName());
            response.put("lastName",user.getLastName());
            response.put("role",jwtTokenProvider.getRoleNames(user.getRoles()));
            response.put("token", token);


            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            //throw  new UsernameNotFoundException("User  not found");
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("checkUsername")
    public ResponseEntity checkUsername(@RequestBody CheckUsernameDto checkUsernameDto){
        Map<Object, Object> response = new HashMap<>();
        response.put("isExist",userService.checkUsername(checkUsernameDto.getUsername()));
        return ResponseEntity.ok(response);
    }

    @PostMapping("checkEmail")
    public ResponseEntity checkEmail(@RequestBody CheckEmailDto checkEmailDto){
        Map<Object, Object> response = new HashMap<>();
        response.put("isExist",userService.checkEmail(checkEmailDto.getEmail()));
        return ResponseEntity.ok(response);
    }

    @PostMapping("checkMobile")
    public ResponseEntity checkMobile(@RequestBody CheckMobileDto checkMobileDto){
        Map<Object, Object> response = new HashMap<>();
        response.put("isExist",userProfileService.checkMobile(checkMobileDto.getMobile()));
        return ResponseEntity.ok(response);
    }
}