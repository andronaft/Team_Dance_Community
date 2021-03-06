package com.zuk.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(schema = "tdcbd" ,name = "feedback")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Feedback extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;



}
