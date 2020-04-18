package com.zuk.service;

import com.zuk.model.Branch;

import java.util.ArrayList;

public interface BranchService {
    ArrayList<Branch> findAll();
    ArrayList<Branch> findAllActive();
    Branch findById(Long id);
    Branch create(Branch branch);
    Branch update(Branch branch);

}
