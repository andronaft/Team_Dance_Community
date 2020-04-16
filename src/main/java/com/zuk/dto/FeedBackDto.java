package com.zuk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zuk.model.Feedback;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedBackDto {
    private Long id;
    private String mobile;
    private String email;
    private String firstName;
    private String lastName;

    public Feedback toFeedback(){
        Feedback feedback = new Feedback();
        feedback.setEmail(email);
        feedback.setFirstName(firstName);
        feedback.setLastName(lastName);
        feedback.setMobile(mobile);

        return feedback;
    }

    public static FeedBackDto fromFeedback(Feedback feedback){
        FeedBackDto dto = new FeedBackDto();
        dto.setEmail(feedback.getEmail());
        dto.setMobile(feedback.getMobile());
        dto.setFirstName(feedback.getFirstName());
        dto.setLastName(feedback.getLastName());
        return dto;
    }
}
