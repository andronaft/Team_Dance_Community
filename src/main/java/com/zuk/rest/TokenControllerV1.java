package com.zuk.rest;

import com.zuk.dto.auth.RegisterUserDto;
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
@RequestMapping(value = "/api/v1/token/")
public class TokenControllerV1 {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public TokenControllerV1( JwtTokenProvider jwtTokenProvider) {

        this.jwtTokenProvider = jwtTokenProvider;
    }

    @RequestMapping(value = "validate")
    public ResponseEntity validate(@RequestHeader("Authorization") String token){//TODO

            Map<Object, Object> response = new HashMap<>();
        System.out.println("12");
        String username;
        username = jwtTokenProvider.getAuthentication(token).getName();
        System.out.println(username);
            response.put("validate", jwtTokenProvider.getAuthentication(token));
            return new ResponseEntity(response, HttpStatus.OK);

    }
}
