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
@Table(schema = "tdcbd" ,name = "branch")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Branch extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "information")
    private String information;

    @Column(name = "contact")
    private String contact;

    @Column(name = "location")
    private String location;

}
