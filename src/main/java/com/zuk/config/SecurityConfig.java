package com.zuk.config;

import com.zuk.security.JwtConfigurer;
import com.zuk.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
    private static final String AUTH_ENDPOINT = "/api/v1/auth/**";
    private static final String BRANCH_ENDPOINT = "/api/v1/branch/**";
    private static final String TRAINING_ENDPOINT = "/api/v1/training/**";
    private static final String TRAINERPUBLIC_ENDPOINT = "/api/v1/userPublic/**";
    private static final String HALLPUBLIC_ENDPOINT = "/api/v1/hallPublic/**";
    private static final String DEEP_ENDPOINT = "/api/deep/**";
    private static final String NEWS_ENDPOINT = "/api/v1/news/**";
    private static final String USERS_ENDPOINT = "/api/v1/users/**";
    private static final String TOKEN_ENDPOINT = "/api/v1/token/**";
    private static final String FEEDBACK_ENDPOINT = "/api/v1/feedback/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_ENDPOINT).permitAll()
                .antMatchers(DEEP_ENDPOINT).permitAll()
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
    }
}