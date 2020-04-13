package com.zuk.service.impl;

import com.zuk.model.UserProfile;
import com.zuk.repository.UserProfileRepository;
import com.zuk.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.zuk.model.Status.NOT_ACTIVE;


@Service
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfile findById(long id) {
        return userProfileRepository.getOne(id);
    }

    @Override
    public UserProfile register(UserProfile userProfile) {
        userProfile.setStatus(NOT_ACTIVE);
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile update(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    public List<UserProfile> getAll() {
        return userProfileRepository.findAll();
    }
}
