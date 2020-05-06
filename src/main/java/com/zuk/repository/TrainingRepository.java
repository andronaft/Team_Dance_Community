package com.zuk.repository;

import com.zuk.model.*;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    ArrayList<Training> findByTimeAfter(Timestamp timestamp);

    ArrayList<Training> findByTrainer(User user);

    ArrayList<Training> findByHallId(Long hallId);

    ArrayList<Training> findByBranchId(Long branchId);

    ArrayList<Training> findByTrainingType(TrainingType trainingType);

}
