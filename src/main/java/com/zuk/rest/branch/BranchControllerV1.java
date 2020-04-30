package com.zuk.rest.branch;

import com.zuk.dto.branch.BranchDto;
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

    @GetMapping(value = "")
    public ResponseEntity findAllActive(){

        return new ResponseEntity<>(BranchDto.fromArrayBranch(branchService.findAllActive()), HttpStatus.OK);
    }

  /*  @GetMapping(value = "{id}/")
    public ResponseEntity findById(@PathVariable(name = "id") Long id ){
        System.out.println("good");
        return new ResponseEntity<>(branchService.findById(id), HttpStatus.OK);
    }*/

    @GetMapping(value = "findById/")
    public ResponseEntity findById(@RequestParam Long id) {
        System.out.println("good");
        return new ResponseEntity<>(BranchDto.fromBranch(branchService.findById(id)), HttpStatus.OK);
    }
}
