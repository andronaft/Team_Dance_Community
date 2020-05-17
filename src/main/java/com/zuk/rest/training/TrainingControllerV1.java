package com.zuk.rest.training;

import com.zuk.dto.training.TrainingDto;
import com.zuk.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/training/")
public class TrainingControllerV1 {//TODO

    private final TrainingService trainingService;

    @Autowired
    public TrainingControllerV1(TrainingService trainingService) {
        this.trainingService = trainingService;
    }


    @GetMapping(value = "{id}")
    public ResponseEntity findById(@PathVariable(name = "id") Long id){

        return new ResponseEntity<>(TrainingDto.fromTraining(trainingService.findById(id)), HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity findAll(){

        return new ResponseEntity<>(TrainingDto.fromArrayTraining(trainingService.findAll()), HttpStatus.OK);
    }

    /*@GetMapping(value = "findByDay")//TODO add search by Date
    public ResponseEntity findByDay(@RequestParam WeekDay weekDay){

        return new ResponseEntity<>(TrainingDto.fromArrayGroupTrainingAdmin(groupTrainingService.findByDay(weekDay)), HttpStatus.OK);
    }*/
    @GetMapping(value = "findByBranch")
    public ResponseEntity findByBranch(@RequestParam Long branchId){
        return new ResponseEntity<>(TrainingDto.fromArrayTraining(trainingService.findByBranch(branchId)), HttpStatus.OK);
    }
}
