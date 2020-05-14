package com.zuk.rest.admin.training;

import com.zuk.dto.training.GroupTrainingDto;
import com.zuk.dto.training.TrainingDto;
import com.zuk.model.WeekDay;
import com.zuk.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v2/admin/training/")
public class AdminTrainingControllerV1 {//TODO idfk whats doing here (create/update)

    private final TrainingService trainingService;

    @Autowired
    public AdminTrainingControllerV1(TrainingService trainingService) {
        this.trainingService = trainingService;
    }


    @GetMapping(value = "{id}")
    public ResponseEntity findById(@PathVariable(name = "id") Long id){

        return new ResponseEntity<>(TrainingDto.fromTrainingWithTrainer (trainingService.findById(id)), HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity findAll(){

        return new ResponseEntity<>(TrainingDto.fromArrayTrainingAdmin(trainingService.findAll()), HttpStatus.OK);
    }

    /*@GetMapping(value = "findByDay")//TODO add search by Date
    public ResponseEntity findByDay(@RequestParam WeekDay weekDay){

        return new ResponseEntity<>(TrainingDto.fromArrayGroupTrainingAdmin(groupTrainingService.findByDay(weekDay)), HttpStatus.OK);
    }*/
    @GetMapping(value = "findByBranch")
    public ResponseEntity findByBranch(@RequestParam Long branchId){
        return new ResponseEntity<>(TrainingDto.fromArrayTrainingAdmin(trainingService.findByBranch(branchId)), HttpStatus.OK);
    }

    @PostMapping(value = "created")
    public ResponseEntity create(/*@RequestBody TrainingDto trainingDto*/){
        System.out.println("hello");
        TrainingDto trainingDto = new TrainingDto();
        System.out.println(trainingDto.toTrainingAdmin());
        return new ResponseEntity<>(trainingService.create(trainingDto.toTrainingAdmin()),HttpStatus.OK);
    }

    @PostMapping(value = "update")
    public ResponseEntity update(@RequestBody TrainingDto trainingDto){
        return new ResponseEntity<>(TrainingDto.fromTrainingAdmin(trainingService.update(trainingDto.toTrainingAdminWithId())),HttpStatus.OK);
    }

    @PostMapping(value = "setTrainer")
    public ResponseEntity setTrainer(@RequestBody TrainingDto trainingDto){
        return new ResponseEntity<>(TrainingDto.fromTrainingWithTrainer(trainingService.saveWithTrainingTrainer(trainingDto.getId(),trainingDto.getTrainerIds())),HttpStatus.OK);
    }
}
