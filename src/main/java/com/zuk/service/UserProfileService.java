package com.zuk.service;

import com.zuk.model.UserProfile;

import java.util.List;

public interface UserProfileService {
    UserProfile findById(long id);
    UserProfile register(UserProfile userProfile);
    UserProfile update(UserProfile userProfile);
    List<UserProfile> getAll();


    Boolean checkMobile(String mobile);
}
