package com.zuk.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(schema = "tdcbd" ,name = "group_training")
@EntityListeners(AuditingEntityListener.class)
@Data
public class GroupTraining extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    private Branch branch;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "time")
    private Time time;

    @Enumerated(EnumType.STRING)
    @Column(name = "week_day")
    private WeekDay weekDay;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = "tdcbd" ,name = "group_training_trainer",
            joinColumns = {@JoinColumn(name = "group_training_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "trainer_id", referencedColumnName = "id")})
    private List<User> trainer;

}
