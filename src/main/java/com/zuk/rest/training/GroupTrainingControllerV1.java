package com.zuk.rest.training;


import com.zuk.dto.branch.BranchDto;
import com.zuk.dto.training.GroupTrainingDto;
import com.zuk.model.WeekDay;
import com.zuk.service.GroupTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
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
       try {
       return new ResponseEntity<>(GroupTrainingDto.fromArrayGroupTraining(groupTrainingService.findAll()), HttpStatus.OK);
       } catch (Exception e) {
           // throw  new UsernameNotFoundException("User  not found");
           //
           return new ResponseEntity<>(GroupTrainingDto.fromArrayGroupTraining(groupTrainingService.findAll()), HttpStatus.OK);
       }
       }

    @GetMapping(value = "findByDay")
    public ResponseEntity findByDay(@RequestParam WeekDay weekDay){

        return new ResponseEntity<>(GroupTrainingDto.fromArrayGroupTraining(groupTrainingService.findByDay(weekDay)), HttpStatus.OK);
    }
    @GetMapping(value = "findByBranch")
    public ResponseEntity findByBranch(@RequestParam Long branchId){

        return new ResponseEntity<>(GroupTrainingDto.fromArrayGroupTraining(groupTrainingService.findByBranch(branchId)), HttpStatus.OK);
    }
}
