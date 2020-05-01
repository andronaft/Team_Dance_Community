package com.zuk.rest.hall;

import com.zuk.dto.hall.HallDto;
import com.zuk.dto.training.GroupTrainingDto;
import com.zuk.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/hallPublic/")
public class HallPublicControllerV1 {

    private final HallService hallService;


    @Autowired
    public HallPublicControllerV1(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity findById(@PathVariable(name = "id") Long id){

        return new ResponseEntity<>(HallDto.fromHallToPublicDto(hallService.findById(id)), HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity findAll(){

        return new ResponseEntity<>(HallDto.fromArrayHallToPublicDto(hallService.findAll()), HttpStatus.OK);

    }
}
