package com.zuk.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(schema = "tdcbd" ,name = "hall")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Hall extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "location")
    private String location;

    @Column(name = "information")
    private String information;

    @OneToMany(mappedBy = "branch")
    private List<GroupTraining> groupTraining;

}
