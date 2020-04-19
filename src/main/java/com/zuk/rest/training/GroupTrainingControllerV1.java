package com.zuk.rest.training;


import com.zuk.dto.branch.BranchDto;
import com.zuk.dto.training.GroupTrainingDto;
import com.zuk.model.WeekDay;
import com.zuk.service.GroupTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/training/groupTraining/")
public class GroupTrainingControllerV1 {
    private final GroupTrainingService groupTrainingService;

    @Autowired
    public GroupTrainingControllerV1(GroupTrainingService groupTrainingService) {
        this.groupTrainingService = groupTrainingService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity findById(@PathVariable(name = "id") Long id){

        return new ResponseEntity<>(GroupTrainingDto.fromGroupTraining(groupTrainingService.findById(id)), HttpStatus.OK);
    }

   @GetMapping(value = "")
    public ResponseEntity findAll(){

       return new ResponseEntity<>(GroupTrainingDto.fromArrayGroupTraining(groupTrainingService.findAll()), HttpStatus.OK);
    }

    @GetMapping(value = "findByDay")
    public ResponseEntity findByDay(@RequestBody GroupTrainingDto groupTrainingDto){

        return new ResponseEntity<>(GroupTrainingDto.fromArrayGroupTraining(groupTrainingService.findByDay(groupTrainingDto.getWeekDay())), HttpStatus.OK);
    }
    @GetMapping(value = "findByBranch")
    public ResponseEntity findByBranch(@RequestBody GroupTrainingDto groupTrainingDto){
        System.out.println(groupTrainingDto.getBranchId());
        return new ResponseEntity<>(GroupTrainingDto.fromArrayGroupTraining(groupTrainingService.findByBranch(groupTrainingDto.getBranchId())), HttpStatus.OK);
    }
}
