package com.zuk.repository;

import com.zuk.model.Branch;
import com.zuk.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
