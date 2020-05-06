package com.zuk.service.impl;

import com.zuk.model.Status;
import com.zuk.model.Training;
import com.zuk.model.User;
import com.zuk.repository.TrainingRepository;
import com.zuk.repository.UserRepository;
import com.zuk.service.TrainingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TrainingServiceImpl  implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository, UserRepository userRepository) {
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Training findById(Long id) {
        return trainingRepository.getOne(id);
    }

    @Override
    public ArrayList<Training> findAll() {
        return (ArrayList<Training>) trainingRepository.findAll();
    }

    @Override
    public ArrayList<Training> findByBranch(Long branchId) {
        return trainingRepository.findByBranchId(branchId);
    }

    @Override
    public ArrayList<Training> findByTrainer(Long trainerId) {
        User trainer = new User(); trainer.setId(trainerId);
        return trainingRepository.findByTrainer(trainer);
    }

    @Override
    public ArrayList<Training> findByTimeAfter(Timestamp time) {
        return trainingRepository.findByTimeAfter(time);
    }

    @Override
    public Training create(Training training) {
        training.setStatus(Status.ACTIVE);
        return trainingRepository.save(training);
    }

    @Override
    public Training update(Training training) {
        Training findTraining = trainingRepository.getOne(training.getId());
        training.setCreated(findTraining.getCreated());
        return trainingRepository.save(training);
    }

    @Override
    public Training saveWithTrainingTrainer(Long id, List<Long> trainerIds) {
        Training training = trainingRepository.getOne(id);
        ArrayList<User> trainerList = new ArrayList<>();
        for(Long trainerId:trainerIds){
            trainerList.add(userRepository.getOne(trainerId));
        }
        training.setTrainer(trainerList);
        return trainingRepository.save(training);
    }
}
