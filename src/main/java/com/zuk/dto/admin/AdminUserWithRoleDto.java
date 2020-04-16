package com.zuk.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zuk.model.Role;
import com.zuk.model.Status;
import com.zuk.model.User;
import com.zuk.security.JwtTokenProvider;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserWithRoleDto {


    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private List<String> roles;



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

    public static AdminUserWithRoleDto fromUser(User user) {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        AdminUserWithRoleDto adminUserWithRoleDto = new AdminUserWithRoleDto();
        adminUserWithRoleDto.setId(user.getId());
        adminUserWithRoleDto.setUsername(user.getUsername());
        adminUserWithRoleDto.setFirstName(user.getFirstName());
        adminUserWithRoleDto.setLastName(user.getLastName());
        adminUserWithRoleDto.setEmail(user.getEmail());
        adminUserWithRoleDto.setStatus(user.getStatus().name());
        adminUserWithRoleDto.setRoles(jwtTokenProvider.getRoleNames(user.getRoles()));
        return adminUserWithRoleDto;
    }
    public static ArrayList<AdminUserWithRoleDto> fromArrayUser(List<User> userList){
        ArrayList<AdminUserWithRoleDto>  adminUserWithRoleDtoArrayList = new ArrayList<>();
        for (User user:userList){
            adminUserWithRoleDtoArrayList.add(fromUser(user));
        }
        return adminUserWithRoleDtoArrayList;
    }
}