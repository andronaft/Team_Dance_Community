package com.zuk.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.net.URL;
import java.sql.Timestamp;



@Entity
@Table(schema = "tdcbd" ,name = "user_profile")
@EntityListeners(AuditingEntityListener.class)
@Data
public class UserProfile {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "social")
    private String social;

    @Column(name = "about")
    private String about;

    @Column(name = "img_url")
    private String imgUrl;//TODO

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "level")
    private Integer level;

    @CreatedDate
    @Column(name = "created")
    private Timestamp created;

    @LastModifiedDate
    @Column(name = "updated")
    private Timestamp updated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;



}
