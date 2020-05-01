package com.zuk.service;

import com.zuk.model.GroupTraining;
import com.zuk.model.WeekDay;

import java.util.ArrayList;
import java.util.List;

public interface GroupTrainingService {
   GroupTraining findById(Long id);
   ArrayList<GroupTraining> findAll();
   ArrayList<GroupTraining> findByDay(WeekDay weekDay);
   ArrayList<GroupTraining> findByBranch(Long branchId);

   GroupTraining create(GroupTraining groupTraining);
   GroupTraining update(GroupTraining groupTraining);
   //ArraList<GroupTraining> findByLocation(Long id)

   GroupTraining saveWithGroupTrainingTrainer(Long id, List<Long> trainerIds);
}
