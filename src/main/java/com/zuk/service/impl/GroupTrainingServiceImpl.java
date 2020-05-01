package com.zuk.service.impl;

import com.zuk.model.*;
import com.zuk.repository.GroupTrainingRepository;
import com.zuk.service.BranchService;
import com.zuk.service.GroupTrainingService;
import com.zuk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class GroupTrainingServiceImpl implements GroupTrainingService {

    private final GroupTrainingRepository groupTrainingRepository;
    private final UserService userService;

    private final BranchService branchService;

    @Autowired
    public GroupTrainingServiceImpl(GroupTrainingRepository groupTrainingRepository, UserService userService, BranchService branchService) {
        this.groupTrainingRepository = groupTrainingRepository;
        this.userService = userService;
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

    @Override
    public GroupTraining saveWithGroupTrainingTrainer(GroupTraining groupTraining) {
        User trainer = userService.findTrainerById((long) 2);
        ArrayList<User> listTrainer = new ArrayList<>();
        listTrainer.add(trainer);
        groupTraining.setTrainer(listTrainer);

        GroupTraining findGroupTraining = groupTrainingRepository.getOne(groupTraining.getId());

        groupTraining.setCreated(findGroupTraining.getCreated());
        return groupTrainingRepository.save(groupTraining);
    }
}
