package com.zuk.rest.training;


import com.zuk.dto.branch.BranchDto;
import com.zuk.dto.training.GroupTrainingDto;
import com.zuk.service.GroupTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/training/")
public class GroupTrainingControllerV1 {
    private final GroupTrainingService groupTrainingService;

    @Autowired
    public GroupTrainingControllerV1(GroupTrainingService groupTrainingService) {
        this.groupTrainingService = groupTrainingService;
    }

    @GetMapping(value = "1")
    public ResponseEntity findById(){
        long id = 1;
        return new ResponseEntity<>(GroupTrainingDto.fromGroupTraining(groupTrainingService.findById(id)), HttpStatus.OK);
    }

   @GetMapping(value = "")
    public ResponseEntity finadAll(){

       return new ResponseEntity<>(GroupTrainingDto.fromArrayGroupTraining(groupTrainingService.findAll()), HttpStatus.OK);
    }
}
