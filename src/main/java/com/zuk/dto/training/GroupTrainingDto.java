package com.zuk.dto.training;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zuk.dto.admin.AdminUserWithRoleDto;
import com.zuk.model.GroupTraining;
import com.zuk.model.Status;
import com.zuk.model.User;
import com.zuk.model.WeekDay;
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
}
