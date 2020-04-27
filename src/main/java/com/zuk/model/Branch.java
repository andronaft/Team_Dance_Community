package com.zuk.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(schema = "tdcbd" ,name = "branch")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class Branch extends BaseEntity  implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "information")
    private String information;

    @Column(name = "contact")
    private String contact;

    @Column(name = "location")
    private String location;

    @Column(name = "img_url")
    private String imgUrl;

    @OneToMany(mappedBy = "branch")
    private List<GroupTraining> groupTraining;
}
