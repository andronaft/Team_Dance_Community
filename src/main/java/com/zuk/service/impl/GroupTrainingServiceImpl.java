package com.zuk.service.impl;

import com.zuk.model.Branch;
import com.zuk.model.GroupTraining;
import com.zuk.model.Status;
import com.zuk.model.WeekDay;
import com.zuk.repository.GroupTrainingRepository;
import com.zuk.service.BranchService;
import com.zuk.service.GroupTrainingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GroupTrainingServiceImpl implements GroupTrainingService {

    private final GroupTrainingRepository groupTrainingRepository;

    private final BranchService branchService;

    @Autowired
    public GroupTrainingServiceImpl(GroupTrainingRepository groupTrainingRepository, BranchService branchService) {
        this.groupTrainingRepository = groupTrainingRepository;
        this.branchService = branchService;
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

    @Override
    public GroupTraining create(GroupTraining groupTraining) {
        return groupTrainingRepository.save(groupTraining);
    }

    @Override
    public GroupTraining update(GroupTraining groupTraining) {
        GroupTraining oldGroupTraining = groupTrainingRepository.getOne(groupTraining.getId());
        groupTraining.setCreated(oldGroupTraining.getCreated());
        return groupTrainingRepository.save(groupTraining);
    }
}
