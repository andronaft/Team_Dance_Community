package com.zuk.service.impl;

import com.zuk.model.Feedback;
import com.zuk.model.Status;
import com.zuk.repository.FeedBackRepository;
import com.zuk.service.FeedBackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@Slf4j
public class FeedBackServiceImpl implements FeedBackService {

    private final FeedBackRepository feedbackRepo;

    @Autowired
    public FeedBackServiceImpl(FeedBackRepository feedbackRepo) {
        this.feedbackRepo = feedbackRepo;
    }



    @Override
    public Feedback apply(Feedback backEntity) {
        backEntity.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        backEntity.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        backEntity.setStatus(Status.NOT_ACTIVE);
        return feedbackRepo.save(backEntity);
    }

    @Override
    public ArrayList<Feedback> findAll() {
        return (ArrayList<Feedback>) feedbackRepo.findAll();
    }

    @Override
    public ArrayList<Feedback> findAllNotActive() {
        return (ArrayList<Feedback>) feedbackRepo.findAllByStatus(Status.NOT_ACTIVE);
    }

    @Override
    public Feedback adminApplyById(Long id) {
        Feedback findFeedBack = feedbackRepo.getOne(id);
        findFeedBack.setStatus(Status.ACTIVE);
        return feedbackRepo.save(findFeedBack);
    }
}
