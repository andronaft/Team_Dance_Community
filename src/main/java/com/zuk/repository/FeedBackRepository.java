package com.zuk.repository;

import com.zuk.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FeedBackRepository extends JpaRepository<Feedback, Long> {
}
