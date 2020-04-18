package com.zuk.repository;

import com.zuk.model.Branch;
import com.zuk.model.Feedback;
import com.zuk.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findAllByStatus(Status status);
}
