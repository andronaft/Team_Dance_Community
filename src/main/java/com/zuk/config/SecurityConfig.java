package com.zuk.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuk.security.JwtConfigurer;
import com.zuk.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Configuration
public class SecurityConfig {

    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
    private static final String AUTH_ENDPOINT = "/api/v1/auth/**";
    private static final String BRANCH_ENDPOINT = "/api/v1/branch/**";
    private static final String TRAINING_ENDPOINT = "/api/v1/training/**";
    private static final String TRAINERPUBLIC_ENDPOINT = "/api/v1/userPublic/**";
    private static final String HALLPUBLIC_ENDPOINT = "/api/v1/hallPublic/**";
    private static final String DEEP_SAVE_ENDPOINTS[] = {"/api/deep/save", "/api/deep/saveget"};
    private static final String DEEP_ENDPOINT = "/api/deep/**";
    private static final String NEWS_ENDPOINT = "/api/v1/news/**";
    private static final String USERS_ENDPOINT = "/api/v1/users/**";
    private static final String TOKEN_ENDPOINT = "/api/v1/token/**";
    private static final String FEEDBACK_ENDPOINT = "/api/v1/feedback/**";

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider, ObjectMapper objectMapper) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.objectMapper = objectMapper;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, e) ->
                        writeError(response, HttpStatus.UNAUTHORIZED, "Authentication required"))
                .accessDeniedHandler((request, response, e) ->
                        writeError(response, HttpStatus.FORBIDDEN, "Access denied"))
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_ENDPOINT).permitAll()
                // the mobile app records deep links anonymously, but only admins may read them
                .antMatchers(DEEP_SAVE_ENDPOINTS).permitAll()
                .antMatchers(DEEP_ENDPOINT).hasRole("ADMIN")
                .antMatchers(FEEDBACK_ENDPOINT).permitAll()
                .antMatchers(TOKEN_ENDPOINT).permitAll()
                .antMatchers(BRANCH_ENDPOINT).permitAll()
                .antMatchers(TRAINING_ENDPOINT).permitAll()
                .antMatchers(TRAINERPUBLIC_ENDPOINT).permitAll()
                .antMatchers(HALLPUBLIC_ENDPOINT).permitAll()
                .antMatchers(NEWS_ENDPOINT).permitAll()
                .antMatchers(USERS_ENDPOINT).hasRole("USER")
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
        return http.build();
    }

    private void writeError(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(), Map.of("status", status.value(), "error", message));
    }
}
