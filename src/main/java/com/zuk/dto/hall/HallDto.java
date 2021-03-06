package com.zuk.dto.hall;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zuk.dto.training.GroupTrainingDto;
import com.zuk.model.GroupTraining;
import com.zuk.model.Hall;
import com.zuk.model.Status;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HallDto {
    private Long id;
    private String name;
    private String information;
    private int capacity;
    private String location;
    private String imgUrl;
    private Status status;
    private Timestamp created;
    private Timestamp updated;

    public static HallDto fromHallToPublicDto(Hall hall){
        HallDto hallDto = new HallDto();
        hallDto.setId(hall.getId());
        hallDto.setName(hall.getName());
        hallDto.setLocation(hall.getLocation());
        hallDto.setInformation(hall.getInformation());
        hallDto.setImgUrl(hall.getImgUrl());
        return hallDto;
    }

    public static HallDto fromHallToAdminDto(Hall hall){
        HallDto hallDto = new HallDto();
        hallDto.setId(hall.getId());
        hallDto.setName(hall.getName());
        hallDto.setCapacity(hall.getCapacity());
        hallDto.setLocation(hall.getLocation());
        hallDto.setInformation(hall.getInformation());
        hallDto.setImgUrl(hall.getImgUrl());
        return hallDto;
    }

    public static ArrayList<HallDto> fromArrayHallToPublicDto(List<Hall> hallList){
        ArrayList<HallDto> hallDtoArrayList = new ArrayList<>();
        for(Hall hall:hallList){
            hallDtoArrayList.add(fromHallToPublicDto(hall));
        }
        return hallDtoArrayList;
    }

    public static ArrayList<HallDto> fromArrayHallToAdminDto(List<Hall> hallList){
        ArrayList<HallDto> hallDtoArrayList = new ArrayList<>();
        for(Hall hall:hallList){
            hallDtoArrayList.add(fromHallToAdminDto(hall));
        }
        return hallDtoArrayList;
    }


    public Hall toHall(){
        Hall hall = new Hall();
        hall.setCapacity(capacity);
        hall.setName(name);
        hall.setLocation(location);
        hall.setInformation(information);
        return hall;
    }

    public Hall toHallWithId(){
        Hall hall = new Hall();
        hall.setId(id);
        hall.setCapacity(capacity);
        hall.setName(name);
        hall.setLocation(location);
        hall.setInformation(information);
        return hall;
    }
}
