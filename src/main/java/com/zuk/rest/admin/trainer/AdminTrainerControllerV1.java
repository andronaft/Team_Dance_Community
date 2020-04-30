package com.zuk.rest.admin.trainer;

import com.zuk.dto.admin.AdminUserWithRoleDto;
import com.zuk.dto.user.UserDto;
import com.zuk.model.User;
import com.zuk.security.JwtTokenProvider;
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
@RequestMapping(value = "/api/v1/admin/trainer")
public class AdminTrainerControllerV1 {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AdminTrainerControllerV1(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping(value = "findByFirstName/")
    public ResponseEntity getTrainerByFirstName(@RequestParam String firstName )
    {
        try {

            Map<Object, Object> response = new HashMap<>();
            ArrayList result = userService.findTrainerByFirstName(firstName);
            if (result.size() == 0) {
                response.put("error","Nothing to show");
                return new ResponseEntity<>(response,HttpStatus.OK);
            }



            return new ResponseEntity<>(AdminUserWithRoleDto.fromArrayUser(result), HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
    @GetMapping(value = "findByLastName/")
    public ResponseEntity getUserByLastName(@RequestParam String lastName)
    {
        try {

            Map<Object, Object> response = new HashMap<>();

            ArrayList result = userService.findTrainerByLastName(lastName);
            if (result.size() == 0) {
                response.put("error","Nothing to show");
                return new ResponseEntity<>(response,HttpStatus.OK);
            }



            return new ResponseEntity<>(AdminUserWithRoleDto.fromArrayUser(result), HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
    @GetMapping(value = "findByFirstNameAndLastName/")
    public ResponseEntity getUserByFirstNameAndLastName(@RequestParam String firstName, String lastName)
    {
        try {
            Map<Object, Object> response = new HashMap<>();

            ArrayList result = userService.findTrainerByFirstNameAndLastName(firstName,lastName);
            if (result.size() == 0) {
                response.put("error","Nothing to show");
                return new ResponseEntity<>(response,HttpStatus.OK);
            }



            return new ResponseEntity<>(AdminUserWithRoleDto.fromArrayUser(result), HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
    @GetMapping(value = "findByUsername/")
    public ResponseEntity getTrainerByUsername(@RequestParam String username)
    {
        User user = userService.findTrainerByUsername(username);
        if(user==null){
            Map<Object, Object> response = new HashMap<>();
            response.put("error","Nothing to show");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(AdminUserWithRoleDto.fromUser(user), HttpStatus.OK);
    }


    @GetMapping(value = "{id}")
    public ResponseEntity getTrainerById(@PathVariable(name = "id") Long id) {
        try {
            User user = userService.findTrainerById(id);

            Map<Object, Object> response = new HashMap<>();

            if (user == null) {
                response.put("error","Nothing to show");
                return new ResponseEntity<>(response,HttpStatus.OK);
            }

            AdminUserWithRoleDto result = AdminUserWithRoleDto.fromUser(user);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (AuthenticationException e) {
            //throw  new UsernameNotFoundException("User  not found");
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping(value = "")
    public ResponseEntity getUser() {
        try {

            List<User> userList = userService.findAllTrainer();

            Map<Object, Object> response = new HashMap<>();
            if (userList.size() == 0) {
                response.put("error","Nothing to show");
                return new ResponseEntity<>(response,HttpStatus.OK);
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

    @GetMapping(value = "setRoleTrainer")
    public ResponseEntity setRoleTrainer(@RequestParam long id){

        User user = userService.setRoleTrainer(id);

        return new ResponseEntity<>(UserDto.fromUserAdmin(user,jwtTokenProvider.getRoleNames(user.getRoles())), HttpStatus.OK);
    }


}