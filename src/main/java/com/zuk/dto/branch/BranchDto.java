package com.zuk.dto.branch;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zuk.model.Branch;
import com.zuk.model.Hall;
import com.zuk.model.Status;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BranchDto {

    private Long id;
    private String name;
    private String information;
    private String contact;
    private String location;
    //private Long hallId;
    private String imgUrl;
    private Status status;
    private Timestamp created;
    private Timestamp updated;

    public Branch toBrunch(){
        Branch branch = new Branch();
        branch.setName(name);
        branch.setInformation(information);
        branch.setContact(contact);
        branch.setLocation(location);
        return branch;
    }
    public Branch toBrunchWithId(){
        Branch branch = new Branch();
        System.out.println("set id");
        branch.setId(id);
        System.out.println("done"+ branch.getId());
        branch.setName(name);
        branch.setInformation(information);
        branch.setContact(contact);
        branch.setLocation(location);
        return branch;
    }
    public static BranchDto fromBranch(Branch branch){
        BranchDto branchDto = new BranchDto();
        branchDto.setId(branch.getId());
        branchDto.setName(branch.getName());
        branchDto.setInformation(branch.getInformation());
        branchDto.setContact(branch.getContact());
        branchDto.setLocation(branch.getLocation());
        return branchDto;
    }
    public static BranchDto fromBranchAdmin(Branch branch){
        BranchDto branchDto = new BranchDto();
        branchDto.setId(branch.getId());
        branchDto.setName(branch.getName());
        branchDto.setInformation(branch.getInformation());
        branchDto.setContact(branch.getContact());
        branchDto.setLocation(branch.getLocation());
        branchDto.setStatus(branch.getStatus());
        branchDto.setCreated(branch.getCreated());
        branchDto.setUpdated(branch.getUpdated());
        return branchDto;
    }

    public static ArrayList<BranchDto> fromArrayBranch(ArrayList<Branch> listBranch){
        ArrayList<BranchDto> arrayBranchDto = new ArrayList<BranchDto>();
        for(Branch branch:listBranch){
            arrayBranchDto.add(fromBranch(branch));
        }
        return arrayBranchDto;
    }
    public static ArrayList<BranchDto> fromArrayBranchAdmin(ArrayList<Branch> listBranch){
        ArrayList<BranchDto> arrayBranchDto = new ArrayList<BranchDto>();
        for(Branch branch:listBranch){
            arrayBranchDto.add(fromBranchAdmin(branch));
        }
        return arrayBranchDto;
    }

}
