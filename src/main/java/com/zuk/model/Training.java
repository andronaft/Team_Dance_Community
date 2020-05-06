package com.zuk.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(schema = "tdcbd" ,name = "training")
@Data
public class Training  extends BaseEntity {

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
    private Timestamp time;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = "tdcbd" ,name = "training_trainer",
            joinColumns = {@JoinColumn(name = "training_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "trainer_id", referencedColumnName = "id")})
    private List<User> trainer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = "tdcbd" ,name = "training_student",
            joinColumns = {@JoinColumn(name = "training_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")})
    private List<User> student;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TrainingType trainingType;
}
