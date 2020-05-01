package com.zuk.rest.admin.training;

import com.zuk.dto.training.GroupTrainingDto;
import com.zuk.model.WeekDay;
import com.zuk.service.GroupTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/admin/training/groupTraining/")
public class AdminGroupTrainingControllerV1 {

    private final GroupTrainingService groupTrainingService;

    @Autowired
    public AdminGroupTrainingControllerV1(GroupTrainingService groupTrainingService) {
        this.groupTrainingService = groupTrainingService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity findById(@PathVariable(name = "id") Long id){

        return new ResponseEntity<>(GroupTrainingDto.fromGroupTrainingWithTrainer(groupTrainingService.findById(id)), HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity findAll(){

        return new ResponseEntity<>(GroupTrainingDto.fromArrayGroupTrainingAdmin(groupTrainingService.findAll()), HttpStatus.OK);
    }

    @GetMapping(value = "findByDay")
    public ResponseEntity findByDay(@RequestParam WeekDay weekDay){

        return new ResponseEntity<>(GroupTrainingDto.fromArrayGroupTrainingAdmin(groupTrainingService.findByDay(weekDay)), HttpStatus.OK);
    }
    @GetMapping(value = "findByBranch")
    public ResponseEntity findByBranch(@RequestParam Long branchId){
        return new ResponseEntity<>(GroupTrainingDto.fromArrayGroupTrainingAdmin(groupTrainingService.findByBranch(branchId)), HttpStatus.OK);
    }

    @PostMapping(value = "create")
    public ResponseEntity create(@RequestBody GroupTrainingDto groupTrainingDto){
        return new ResponseEntity<>(groupTrainingService.create(groupTrainingDto.toGroupTrainingAdmin()),HttpStatus.OK);
    }

    @PostMapping(value = "update")
    public ResponseEntity update(@RequestBody GroupTrainingDto groupTrainingDto){
        return new ResponseEntity<>(GroupTrainingDto.fromGroupTrainingAdmin(groupTrainingService.update(groupTrainingDto.toGroupTrainingAdminWithId())),HttpStatus.OK);
    }

    @PostMapping(value = "setTrainer")
    public ResponseEntity setTrainer(@RequestBody GroupTrainingDto groupTrainingDto){
        return new ResponseEntity<>(GroupTrainingDto.fromGroupTrainingAdmin(groupTrainingService.saveWithGroupTrainingTrainer(groupTrainingDto.toGroupTrainingAdminWithId())),HttpStatus.OK);
    }
}
