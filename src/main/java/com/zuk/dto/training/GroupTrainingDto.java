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
    private List<Long> trainerIds;


    public static GroupTrainingDto fromGroupTrainingAdmin(GroupTraining groupTraining){
        GroupTrainingDto groupTrainingDto = new GroupTrainingDto();
        groupTrainingDto.setId(groupTraining.getId());
        groupTrainingDto.setName(groupTraining.getName());
        groupTrainingDto.setBranchId(groupTraining.getBranch().getId());
        groupTrainingDto.setCapacity(groupTraining.getCapacity());
        groupTrainingDto.setTime(groupTraining.getTime());
        groupTrainingDto.setWeekDay(groupTraining.getWeekDay());
        groupTrainingDto.setCreated(groupTraining.getCreated());
        groupTrainingDto.setUpdated(groupTraining.getUpdated());
        groupTrainingDto.setStatus(groupTraining.getStatus());
        return groupTrainingDto;
    }

    public static GroupTrainingDto fromGroupTrainingWithTrainer(GroupTraining groupTraining){
        GroupTrainingDto groupTrainingDto = fromGroupTrainingAdmin(groupTraining);
        List<User> list =  groupTraining.getTrainer();
        List<Long> ids = new ArrayList<>();
        for(User user:list){
            ids.add(user.getId());
        }
        groupTrainingDto.setTrainerIds(ids);
        return groupTrainingDto;
    }
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

    public static ArrayList<GroupTrainingDto> fromArrayGroupTrainingAdmin(List<GroupTraining> groupTrainingsList){
        ArrayList<GroupTrainingDto>  groupTrainingDtoArrayList = new ArrayList<>();
        for (GroupTraining groupTraining:groupTrainingsList){
            groupTrainingDtoArrayList.add(fromGroupTrainingWithTrainer(groupTraining));
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

    public GroupTraining toGroupTrainingAdminWithId(){
        GroupTraining groupTraining = new GroupTraining();
        groupTraining.setId(id);
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
