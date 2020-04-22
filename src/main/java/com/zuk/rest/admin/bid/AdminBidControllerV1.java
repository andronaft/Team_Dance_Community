package com.zuk.rest.admin.bid;

import com.zuk.dto.FeedBackDto;
import com.zuk.dto.admin.AdminUserWithRoleDto;
import com.zuk.dto.admin.feedback.AdminFeedbackDto;
import com.zuk.model.Feedback;
import com.zuk.service.FeedBackService;
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
@RequestMapping(value = "/api/v1/admin/bid/")
public class AdminBidControllerV1 {

    private final FeedBackService feedBackService;

    @Autowired
    public AdminBidControllerV1(FeedBackService feedBackService) {
        this.feedBackService = feedBackService;
    }

    @GetMapping(value = "findAllFeedback/")
    public ResponseEntity findAllFeedback()
    {
        Map<Object, Object> response = new HashMap<>();
        try {

            ArrayList result = feedBackService.findAll();
            if (result.size() == 0) {
                response.put("error","No feedback bid");
                return new ResponseEntity<>(response,HttpStatus.OK);
            }


            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping(value = "findAllNotActiveFeedback/")
    public ResponseEntity findAllNotActiveFeedback()
    {
        Map<Object, Object> response = new HashMap<>();
        try {

            ArrayList result = feedBackService.findAllNotActive();
            if (result.size() == 0) {
                response.put("error","No Not_Active feedback bid");
                return new ResponseEntity<>(response,HttpStatus.OK);
            }


            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping(value = "applyFeedback/")
    public ResponseEntity setActive(@RequestBody FeedBackDto feedBackDto)
    {
        Map<Object, Object> response = new HashMap<>();
        try {

            Feedback feedback = feedBackService.adminApplyById(feedBackDto.getId());
            if (feedback == null) {
                response.put("error","Can`t apply feedback");
                return new ResponseEntity<>(response,HttpStatus.OK);
            }


            return new ResponseEntity<>(feedback, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
