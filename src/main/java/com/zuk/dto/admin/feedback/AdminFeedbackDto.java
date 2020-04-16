package com.zuk.dto.admin.feedback;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zuk.model.Status;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminFeedbackDto {
    private Long id;
    private String mobile;
    private String email;
    private String firstName;
    private String lastName;
    private Timestamp created;
    private Timestamp updated;
    private Status status;
}
