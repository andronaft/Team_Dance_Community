package com.zuk.rest.admin.users;

import com.zuk.dto.admin.AdminUserDto;
import com.zuk.dto.admin.AdminUserWithRoleDto;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/admin/users/")
public class AdminUserControllerV1 {

    private final UserService userService;

    @Autowired
    public AdminUserControllerV1(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "findByFirstName/")
    public ResponseEntity<List<AdminUserWithRoleDto>> getUserByFirstName(@RequestBody UserDto userDto)
    {
        try {

            ArrayList result = userService.findByFirstName(userDto.getFirstName());
            if (result.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Map<Object, Object> response = new HashMap<>();


            return new ResponseEntity<>(AdminUserWithRoleDto.fromArrayUser(result), HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
    @GetMapping(value = "findByLastName/")
    public ResponseEntity<List<AdminUserWithRoleDto>> getUserByLastName(@RequestBody UserDto userDto)
    {
        try {

            ArrayList result = userService.findByLastName(userDto.getLastName());
            if (result.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Map<Object, Object> response = new HashMap<>();


            return new ResponseEntity<>(AdminUserWithRoleDto.fromArrayUser(result), HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
    @GetMapping(value = "findByFirstNameAndLastName/")
    public ResponseEntity<List<AdminUserWithRoleDto>> getUserByFirstNameAndLastName(@RequestBody UserDto userDto)
    {
        try {

            ArrayList result = userService.findByFirstNameAndLastName(userDto.getFirstName(),userDto.getLastName());
            if (result.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Map<Object, Object> response = new HashMap<>();


            return new ResponseEntity<>(AdminUserWithRoleDto.fromArrayUser(result), HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
    @GetMapping(value = "findByUsername/")
    public ResponseEntity<AdminUserWithRoleDto> getUserByUsername(@RequestBody UserDto userDto)
    {
        Long id = userService.findByUsername(userDto.getUsername()).getId();
        return getUserById(id);
    }

    @GetMapping(value = "findByEmail/")
    public ResponseEntity<AdminUserWithRoleDto> getUserByEmail(@RequestBody UserDto userDto)
    {
        Long id = userService.findByEmail(userDto.getEmail()).getId();
        return getUserById(id);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<AdminUserWithRoleDto> getUserById(@PathVariable(name = "id") Long id) {
        try {
            User user = userService.findById(id);

            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            AdminUserWithRoleDto result = AdminUserWithRoleDto.fromUser(user);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (AuthenticationException e) {
            //throw  new UsernameNotFoundException("User  not found");
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<List<AdminUserWithRoleDto>> getUser() {
        try {

            List<User> userList = userService.getAll();

            if (userList.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            ArrayList<AdminUserWithRoleDto> result = AdminUserWithRoleDto.fromArrayUser(userList);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping(value = "activateUser/")
    public ResponseEntity activateUser(@RequestBody UserDto userDto) {
        try {
            return new ResponseEntity<>(AdminUserWithRoleDto.fromUser(userService.activateUser(userDto.getId())), HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }

    }
}