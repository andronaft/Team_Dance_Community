package com.zuk.service;

import com.zuk.model.GroupTraining;
import com.zuk.model.WeekDay;

import java.util.ArrayList;

public interface GroupTrainingService {
   GroupTraining findById(Long id);
   ArrayList<GroupTraining> findAll();
   ArrayList<GroupTraining> findByDay(WeekDay weekDay);
   ArrayList<GroupTraining> findByBranch(Long branchId);
   //ArraList<GroupTraining> findByLocation(Long id)
}
