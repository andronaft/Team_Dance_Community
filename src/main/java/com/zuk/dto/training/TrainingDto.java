package com.zuk.dto.training;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zuk.model.*;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainingDto {
    private Long id;
    private String name;
    private Long branchId;
    private int capacity;
    //private String location;
    private Long hallId;
    private Timestamp time;

    private Timestamp created;
    private Timestamp updated;
    private Status status;
    private List<Long> trainerIds;


    private TrainingType trainingType;


    public static TrainingDto fromTrainingAdmin(Training training){
        TrainingDto trainingDto = new TrainingDto();
        trainingDto.setId(training.getId());
        trainingDto.setName(training.getName());
        trainingDto.setBranchId(training.getBranch().getId());

        trainingDto.setHallId(training.getHall().getId());

        trainingDto.setCapacity(training.getCapacity());
        trainingDto.setTime(training.getTime());

        trainingDto.setCreated(training.getCreated());
        trainingDto.setUpdated(training.getUpdated());
        trainingDto.setStatus(training.getStatus());

        trainingDto.setTrainingType(training.getTrainingType());
        return trainingDto;
    }

    public static TrainingDto fromTrainingAdminWithTrainer(Training training){
        TrainingDto trainingDto = fromTrainingAdmin(training);
        List<User> list =  training.getTrainer();
        List<Long> ids = new ArrayList<>();
        for(User user:list){
            ids.add(user.getId());
        }
        trainingDto.setTrainerIds(ids);
        return trainingDto;
    }

    public static TrainingDto fromTrainingWithTrainer(Training training){
        TrainingDto trainingDto = fromTraining(training);
        List<User> list =  training.getTrainer();
        List<Long> ids = new ArrayList<>();
        for(User user:list){
            ids.add(user.getId());
        }
        trainingDto.setTrainerIds(ids);
        return trainingDto;
    }

    public static TrainingDto fromTraining(Training training){
        TrainingDto trainingDto = new TrainingDto();
        trainingDto.setId(training.getId());
        trainingDto.setName(training.getName());
        trainingDto.setBranchId(training.getBranch().getId());

        trainingDto.setHallId(training.getHall().getId());

        trainingDto.setCapacity(training.getCapacity());
        trainingDto.setTime(training.getTime());

        trainingDto.setTrainingType(training.getTrainingType());
        return trainingDto;
    }

    public static ArrayList<TrainingDto> fromArrayTraining(List<Training> trainingsList){
        ArrayList<TrainingDto>  trainingDtoArrayList = new ArrayList<>();
        for (Training training:trainingsList){
            trainingDtoArrayList.add(fromTrainingWithTrainer(training));
        }
        return trainingDtoArrayList;
    }

    public static ArrayList<TrainingDto> fromArrayTrainingAdmin(List<Training> trainingsList){
        ArrayList<TrainingDto>  trainingDtoArrayList = new ArrayList<>();
        for (Training training:trainingsList){
            trainingDtoArrayList.add(fromTrainingAdminWithTrainer(training));
        }
        return trainingDtoArrayList;
    }

    public Training toTrainingAdmin(){
        Training training = new Training();
        //training.setId(id);
        training.setName(name);

        Hall hall = new Hall();hall.setId(hallId);
        training.setHall(hall);
        // training.setLocation(location);
        Branch branch = new Branch();branch.setId(branchId);
        training.setBranch(branch);
        training.setCapacity(capacity);
        training.setTime(time);

        ArrayList<User> arrayListTrainer = new ArrayList<>();
        for(Long id:trainerIds){
            User user = new User();
            user.setId(id);
            arrayListTrainer.add(user);
        }
        training.setTrainer(arrayListTrainer);


        training.setStatus(status);

        training.setTrainingType(trainingType);
        return training;
    }

    public Training toTrainingAdminWithId(){
        Training training = new Training();
        training.setId(id);
        training.setName(name);
        Hall hall = new Hall();hall.setId(hallId);
        training.setHall(hall);
        Branch branch = new Branch();branch.setId(branchId);
        training.setBranch(branch);
        training.setCapacity(capacity);
        training.setTime(time);
        ArrayList<User> trainerList = new ArrayList<>();
        for(Long id: trainerIds){
            User trainer = new User();
            trainer.setId(id);
            trainerList.add(trainer);
        }
        training.setTrainer(trainerList);
        training.setStatus(status);

        training.setTrainingType(trainingType);
        return training;
    }
}
