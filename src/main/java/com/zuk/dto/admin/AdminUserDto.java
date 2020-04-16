package com.zuk.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zuk.model.Role;
import com.zuk.model.Status;
import com.zuk.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    //private List<Role> roles;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setStatus(Status.valueOf(status));
        return user;
    }

    public static AdminUserDto fromUser(User user) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setId(user.getId());
        adminUserDto.setUsername(user.getUsername());
        adminUserDto.setFirstName(user.getFirstName());
        adminUserDto.setLastName(user.getLastName());
        adminUserDto.setEmail(user.getEmail());
        adminUserDto.setStatus(user.getStatus().name());
        return adminUserDto;
    }
    public static ArrayList<AdminUserDto> fromArrayUser(List<User> userList){
        ArrayList<AdminUserDto>  adminUserDtoArrayList = new ArrayList<>();
        for (User user:userList){
            adminUserDtoArrayList.add(fromUser(user));
        }
        return adminUserDtoArrayList;
    }
}