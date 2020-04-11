package com.zuk.rest;

import com.zuk.dto.FeedBackDto;
import com.zuk.dto.UserDto;
import com.zuk.model.Feedback;
import com.zuk.service.impl.FeedBackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/feedback/")
public class FeedBackControllerV1 {

    private final FeedBackServiceImpl feedBackService;

    @Autowired
    public FeedBackControllerV1(FeedBackServiceImpl feedBackService) {
        this.feedBackService = feedBackService;
    }
    @PostMapping("apply")
    public ResponseEntity<FeedBackDto> apply(@RequestBody FeedBackDto feedBackDto){
        Feedback feedback = feedBackService.apply(feedBackDto.toFeedback());
        System.out.println(feedback.toString());
        if(feedback == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        FeedBackDto result = FeedBackDto.fromFeedback(feedback);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
