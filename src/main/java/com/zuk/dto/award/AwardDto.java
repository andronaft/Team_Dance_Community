package com.zuk.dto.award;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zuk.dto.branch.BranchDto;
import com.zuk.model.Award;
import com.zuk.model.Branch;
import com.zuk.model.Status;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AwardDto {
    private Long id;
    private String name;
    private String imgUrl;
    private Timestamp created;
    private Timestamp updated;
    private Status status;
    private String description;

    public static AwardDto fromAward(Award award){
        AwardDto awardDto = new AwardDto();
        awardDto.setId(award.getId());
        awardDto.setName(award.getName());
        awardDto.setImgUrl(award.getImgUrl());
        awardDto.setDescription(award.getDescription());
        return awardDto;
    }


    public static ArrayList<AwardDto> fromArrayAward(ArrayList<Award> listAward){
        ArrayList<AwardDto> arrayAwardDto = new ArrayList<AwardDto>();
        for(Award award:listAward){
            arrayAwardDto.add(fromAward(award));
        }
        return arrayAwardDto;
    }
}
