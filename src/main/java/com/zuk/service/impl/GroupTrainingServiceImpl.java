package com.zuk.service.impl;

import com.zuk.model.GroupTraining;
import com.zuk.model.WeekDay;
import com.zuk.repository.GroupTrainingRepository;
import com.zuk.service.GroupTrainingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class GroupTrainingServiceImpl implements GroupTrainingService {

    private final GroupTrainingRepository groupTrainingRepository;

    @Autowired
    public GroupTrainingServiceImpl(GroupTrainingRepository groupTrainingRepository) {
        this.groupTrainingRepository = groupTrainingRepository;
    }

    @Override
    public GroupTraining findById(Long id) {
        return groupTrainingRepository.getOne(id);
    }

    @Override
    public ArrayList<GroupTraining> findAll() {
        return (ArrayList<GroupTraining>) groupTrainingRepository.findAll();
    }

    @Override
    public ArrayList<GroupTraining> findByDay(WeekDay weekDay) {
        return (ArrayList<GroupTraining>) groupTrainingRepository.findAllByWeekDay(weekDay);
    }

    @Override
    public ArrayList<GroupTraining> findByBranch(Long branchId) {
        return (ArrayList<GroupTraining>) groupTrainingRepository.findAllByBranchId(branchId);
    }
}
