package com.zuk.model;

import lombok.Data;
import lombok.ToString;
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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    private Branch branch;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "time")
    private Timestamp time;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = "tdcbd" ,name = "training_trainer",
            joinColumns = {@JoinColumn(name = "training_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "trainer_id", referencedColumnName = "id")})
    private List<User> trainer;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = "tdcbd" ,name = "training_student",
            joinColumns = {@JoinColumn(name = "training_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")})
    private List<User> student;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TrainingType trainingType;
}
