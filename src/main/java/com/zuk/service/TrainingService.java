package com.zuk.service;

import com.zuk.model.GroupTraining;
import com.zuk.model.Training;
import com.zuk.model.User;
import com.zuk.model.WeekDay;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public interface TrainingService {

    Training findById(Long id);

    ArrayList<Training> findAll();

    ArrayList<Training> findByBranch(Long branchId);

    ArrayList<Training> findByTrainer(Long trainerId);

    ArrayList<Training> findByTimeAfter(Timestamp time);

    Training create(Training training);
    Training update(Training training);


    //ArraList<GroupTraining> findByLocation(Long id)

    Training saveWithTrainingTrainer(Long id, List<Long> trainerIds);
}
