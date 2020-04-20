package com.zuk.rest.admin.training;

import com.zuk.dto.training.GroupTrainingDto;
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

        return new ResponseEntity<>(groupTrainingService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity findAll(){

        return new ResponseEntity<>(groupTrainingService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "findByDay")
    public ResponseEntity findByDay(@RequestBody GroupTrainingDto groupTrainingDto){

        return new ResponseEntity<>(groupTrainingService.findByDay(groupTrainingDto.getWeekDay()), HttpStatus.OK);
    }
    @GetMapping(value = "findByBranch")
    public ResponseEntity findByBranch(@RequestBody GroupTrainingDto groupTrainingDto){
        return new ResponseEntity<>(groupTrainingService.findByBranch(groupTrainingDto.getBranchId()), HttpStatus.OK);
    }

    @PostMapping(value = "create")
    public ResponseEntity create(@RequestBody GroupTrainingDto groupTrainingDto){
        System.out.println(groupTrainingDto);
        System.out.println(groupTrainingDto.toGroupTrainingAdmin());
        return new ResponseEntity<>(groupTrainingService.create(groupTrainingDto.toGroupTrainingAdmin()),HttpStatus.OK);
    }
}
