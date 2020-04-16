package com.zuk.service;

import com.zuk.model.Feedback;

import java.util.ArrayList;

public interface FeedBackService {
    Feedback apply(Feedback backEntity);
    ArrayList<Feedback> findAll();
    ArrayList<Feedback> findAllNotActive();
    Feedback adminApplyById(Long id);
}
