package com.zuk.rest;

import com.zuk.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/token/")
public class TokenControllerV1 {

    private final JwtTokenProvider jwtTokenProvider;

    public TokenControllerV1(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @RequestMapping(value = "validate")
    public ResponseEntity<Map<String, Boolean>> validate(@RequestHeader(value = "Authorization", required = false) String header) {
        String token = jwtTokenProvider.stripPrefix(header);
        return ResponseEntity.ok(Map.of("validate", jwtTokenProvider.validateToken(token)));
    }
}
