package com.zuk.dto.training;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zuk.dto.admin.AdminUserWithRoleDto;
import com.zuk.model.*;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupTrainingDto {
    private Long id;
    private String name;
    private Long branchId;
    private int capacity;
    private String location;
    private Time time;
    private WeekDay weekDay;
    private Timestamp created;
    private Timestamp updated;
    private Status status;

    public static GroupTrainingDto fromGroupTraining(GroupTraining groupTraining){
        GroupTrainingDto groupTrainingDto = new GroupTrainingDto();
        groupTrainingDto.setId(groupTraining.getId());
        groupTrainingDto.setName(groupTraining.getName());
        groupTrainingDto.setBranchId(groupTraining.getBranch().getId());
        groupTrainingDto.setCapacity(groupTraining.getCapacity());
        groupTrainingDto.setTime(groupTraining.getTime());
        groupTrainingDto.setWeekDay(groupTraining.getWeekDay());
        return groupTrainingDto;
    }

    public static ArrayList<GroupTrainingDto> fromArrayGroupTraining(List<GroupTraining> groupTrainingsList){
        ArrayList<GroupTrainingDto>  groupTrainingDtoArrayList = new ArrayList<>();
        for (GroupTraining groupTraining:groupTrainingsList){
            groupTrainingDtoArrayList.add(fromGroupTraining(groupTraining));
        }
        return groupTrainingDtoArrayList;
    }

    public GroupTraining toGroupTrainingAdmin(){
        GroupTraining groupTraining = new GroupTraining();
        //groupTraining.setId(id);
        groupTraining.setName(name);
        groupTraining.setLocation(location);
        Branch branch = new Branch();branch.setId(branchId);
        groupTraining.setBranch(branch);
        groupTraining.setCapacity(capacity);
        groupTraining.setTime(time);
        groupTraining.setWeekDay(weekDay);
        groupTraining.setStatus(status);
        return groupTraining;
    }
}
