package com.zuk.service;

import com.zuk.model.Award;
import com.zuk.model.User;

import java.util.ArrayList;

public interface AwardService {
    ArrayList<Award> findByUser(User user);
}
