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
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }


}
