package com.zuk.service.impl;


import com.zuk.model.Branch;
import com.zuk.repository.BranchRepository;
import com.zuk.service.BranchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public ArrayList<Branch> findAll() {
        return (ArrayList<Branch>) branchRepository.findAll();
    }

    @Override
    public Branch findById(Long id) {
        return branchRepository.getOne(id);
    }
}
