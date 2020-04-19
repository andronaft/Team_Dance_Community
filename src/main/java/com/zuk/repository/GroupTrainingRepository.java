package com.zuk.repository;

import com.zuk.model.Feedback;
import com.zuk.model.GroupTraining;
import com.zuk.model.WeekDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupTrainingRepository extends JpaRepository<GroupTraining, Long> {
    List<GroupTraining> findAllByWeekDay(WeekDay weekDay);
    List<GroupTraining> findAllByBranchId(Long id);
}
