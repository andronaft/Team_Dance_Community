package com.zuk.rest.admin.users;

import com.zuk.dto.admin.AdminUserDto;
import com.zuk.dto.user.UserDto;
import com.zuk.model.User;
import com.zuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin/users/")
public class AdminUserControllerV1 {

    private final UserService userService;

    @Autowired
    public AdminUserControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "findByUsername/")
    public ResponseEntity<AdminUserDto> getUserByUsername(@RequestBody UserDto userDto)
    {
        Long id = userService.findByUsername(userDto.getUsername()).getId();
        return getUserById(id);
    }

    @GetMapping(value = "findByEmail/")
    public ResponseEntity<AdminUserDto> getUserByEmail(@RequestBody UserDto userDto)
    {
        Long id = userService.findByEmail(userDto.getEmail()).getId();
        return getUserById(id);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        try {
            User user = userService.findById(id);

            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            AdminUserDto result = AdminUserDto.fromUser(user);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (AuthenticationException e) {
            //throw  new UsernameNotFoundException("User  not found");
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<List<AdminUserDto>> getUser() {
        try {

            List<User> userList = userService.getAll();

            if (userList.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            ArrayList<AdminUserDto> result = AdminUserDto.fromArrayUser(userList);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (AuthenticationException e) {
            //throw  new UsernameNotFoundException("User  not found");
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
