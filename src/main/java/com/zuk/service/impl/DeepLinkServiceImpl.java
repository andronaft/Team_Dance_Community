package com.zuk.service.impl;


import com.zuk.model.DeepLink;
import com.zuk.model.Status;
import com.zuk.repository.DeepLinkRepository;
import com.zuk.service.DeepLinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class DeepLinkServiceImpl implements DeepLinkService {

    @Autowired
    private final DeepLinkRepository deepLinkRepository;

    public DeepLinkServiceImpl(DeepLinkRepository deepLinkRepository) {
        this.deepLinkRepository = deepLinkRepository;
    }

    @Override
    public ArrayList<DeepLink> findAll() {
        return (ArrayList<DeepLink>) deepLinkRepository.findAll();
    }

    @Override
    public ArrayList<DeepLink> findAllByFrom(String from) {
        return (ArrayList<DeepLink>) deepLinkRepository.findAllByFrom(from);
    }

    @Override
    public DeepLink save(DeepLink deepLink) {
        deepLink.setStatus(Status.ACTIVE);
        return deepLinkRepository.save(deepLink);
    }

    @Override
    public ArrayList<DeepLink> findAllByFromConsist(String string) {
        return (ArrayList<DeepLink>) deepLinkRepository.findAllByFromContains(string);
    }
}
