package com.zuk.service.impl;


import com.zuk.model.Branch;
import com.zuk.model.Status;
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
    public ArrayList<Branch> findAllActive() {
        return (ArrayList<Branch>) branchRepository.findAllByStatus(Status.ACTIVE);
    }

    @Override
    public Branch findById(Long id) {
        return branchRepository.getOne(id);
    }

    @Override
    public Branch create(Branch branch) {
        branch.setStatus(Status.NOT_ACTIVE);
        return branchRepository.save(branch);
    }

    @Override
    public Branch update(Branch branch) {
        System.out.println("update " + branch);
        Branch branchFind = findById(branch.getId());

        branch.setCreated(branchFind.getCreated());


        return branchRepository.save(branch);
    }
}
