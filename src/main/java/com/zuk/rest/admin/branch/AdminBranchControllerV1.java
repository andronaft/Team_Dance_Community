package com.zuk.rest.admin.branch;

import com.zuk.dto.branch.BranchDto;
import com.zuk.model.Branch;
import com.zuk.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/admin/branch/")
public class AdminBranchControllerV1 {
    private final BranchService branchService;

    @Autowired
    public AdminBranchControllerV1(BranchService branchService) {
        this.branchService = branchService;
    }


    @PostMapping(value = "update")
    public ResponseEntity updateById(@RequestBody BranchDto branchDto){

        Branch branch = branchDto.toBrunch();
        branch.setId(branchDto.getId());
        branch.setStatus(branchDto.getStatus());
        return new ResponseEntity<>(branchService.update(branch), HttpStatus.OK);
    }

    @PostMapping(value = "create")
    public ResponseEntity create(@RequestBody BranchDto branchDto){
        return new ResponseEntity<>(branchService.create(branchDto.toBrunch()), HttpStatus.OK);
    }


    @GetMapping(value = "")
    public ResponseEntity<List<Branch>> findAll(){

        return new ResponseEntity<>(branchService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "{id}" )
    public ResponseEntity findById(@PathVariable(name = "id") long id){
        System.out.println("good");
        return new ResponseEntity<>(branchService.findById((long) id), HttpStatus.OK);
    }
}
