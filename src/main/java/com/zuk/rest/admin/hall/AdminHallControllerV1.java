package com.zuk.rest.admin.hall;

import com.zuk.dto.hall.HallDto;
import com.zuk.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/admin/hall/")
public class AdminHallControllerV1 {

    private final HallService hallService;


    @Autowired
    public AdminHallControllerV1(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity findById(@PathVariable(name = "id") Long id){

        return new ResponseEntity<>(HallDto.fromHallToAdminDto(hallService.findById(id)), HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity findAll(){

        return new ResponseEntity<>(HallDto.fromArrayHallToAdminDto(hallService.findAll()), HttpStatus.OK);

    }

    @PostMapping(value = "create/")
    public ResponseEntity create(@RequestBody HallDto hallDto){

        return new ResponseEntity<>(hallService.create(hallDto.toHall()), HttpStatus.OK);
    }

    @PostMapping(value = "update/")
    public ResponseEntity update(@RequestBody HallDto hallDto){

        return new ResponseEntity<>(hallService.update(hallDto.toHallWithId()), HttpStatus.OK);
    }

}
