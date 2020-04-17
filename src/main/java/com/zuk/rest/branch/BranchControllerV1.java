package com.zuk.rest.branch;

import com.zuk.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/branch/")
public class BranchControllerV1 {
    private final BranchService branchService;

    @Autowired
    public BranchControllerV1(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping(value = "findAll/")
    public ResponseEntity findAll(){

        return new ResponseEntity<>(branchService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity findById(@PathVariable(name = "id") Long id){

        return new ResponseEntity<>(branchService.findById(id), HttpStatus.OK);
    }
}
