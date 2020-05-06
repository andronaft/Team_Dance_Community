package com.zuk.repository;

import com.zuk.model.Award;
import com.zuk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface AwardRepository extends JpaRepository<Award, Long> {
    List<Award> findByUsers(User user);
}
