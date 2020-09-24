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
@Table(schema = "tdcbd" ,name = "deep_link")
@EntityListeners(AuditingEntityListener.class)
@Data
public class DeepLink extends BaseEntity{

    @Column(name = "fromid")
    private String from;

    @Column(name = "url_webview")
    private String url_webview;


}
