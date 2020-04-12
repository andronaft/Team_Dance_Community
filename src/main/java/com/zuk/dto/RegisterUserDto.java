package com.zuk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zuk.model.User;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterUserDto {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    public User toUser(){
        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setEmail(email);

        return user;
    }

    public static RegisterUserDto fromUser(User user) {
        RegisterUserDto userDto = new RegisterUserDto();
        //userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}
