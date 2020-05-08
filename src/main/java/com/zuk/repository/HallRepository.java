package com.zuk.repository;

import com.zuk.model.GroupTraining;
import com.zuk.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall, Long> {
    Hall findByName(String name);
}
