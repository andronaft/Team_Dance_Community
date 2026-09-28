package com.zuk.dto.auth;

import lombok.Data;
import lombok.ToString;

@Data
public class AuthenticationRequestDto {
    private String username;
    @ToString.Exclude
    private String password;
}
