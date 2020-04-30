package com.zuk.rest.admin.trainer;

import com.zuk.dto.user.UserDto;
import com.zuk.model.User;
import com.zuk.security.JwtTokenProvider;
import com.zuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "setRoleTrainer")
    public ResponseEntity setRoleTrainer(@RequestParam long id){

        User user = userService.setRoleTrainer(id);

        return new ResponseEntity<>(UserDto.fromUserAdmin(user,jwtTokenProvider.getRoleNames(user.getRoles())), HttpStatus.OK);
    }


}