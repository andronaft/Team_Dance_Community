package com.zuk.rest;

import com.zuk.dto.AdminUserDto;
import com.zuk.model.User;
import com.zuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;

    @Autowired
    public AdminRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "user/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "users/")
    public ResponseEntity<List<AdminUserDto>> getUser() {
        List<User> userList = userService.getAll();

        if (userList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ArrayList<AdminUserDto> result = AdminUserDto.fromArrayUser(userList);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}