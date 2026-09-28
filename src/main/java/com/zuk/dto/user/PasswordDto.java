package com.zuk.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(exclude = {"oldPassword", "newPassword"})
public class PasswordDto {
    private String oldPassword;
    private String newPassword;
}
