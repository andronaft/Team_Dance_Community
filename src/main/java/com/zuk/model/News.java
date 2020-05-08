package com.zuk.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(schema = "tdcbd" ,name = "news")
@EntityListeners(AuditingEntityListener.class)
@Data
public class News extends  BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "text")
    private String text;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "data")
    private Timestamp data;
}
