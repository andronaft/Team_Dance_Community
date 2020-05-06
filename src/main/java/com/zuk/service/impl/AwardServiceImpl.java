package com.zuk.service.impl;


import com.zuk.model.Award;
import com.zuk.model.User;
import com.zuk.repository.AwardRepository;
import com.zuk.service.AwardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class AwardServiceImpl implements AwardService {//TODO add to controller

    private final AwardRepository awardRepository;

    @Autowired
    public AwardServiceImpl(AwardRepository awardRepository) {
        this.awardRepository = awardRepository;
    }


    @Override
    public ArrayList<Award> findByUser(User user) {
        return (ArrayList<Award>) awardRepository.findByUsers(user);
    }
}
