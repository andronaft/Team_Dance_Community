package com.zuk.service;

import com.zuk.model.Branch;

import java.util.ArrayList;

public interface BranchService {
    ArrayList<Branch> findAll();
    Branch findById(Long id);
}
