package com.zuk.repository;

import com.zuk.model.Feedback;
import com.zuk.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FeedBackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findAllByStatus(Status status);
}
