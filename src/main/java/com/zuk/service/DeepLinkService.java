package com.zuk.service;

import com.zuk.model.DeepLink;

import java.util.ArrayList;

public interface DeepLinkService {
    ArrayList<DeepLink> findAll();
    ArrayList<DeepLink> findAllByFrom(String from);
    DeepLink save(DeepLink deepLink);
    ArrayList<DeepLink> findAllByFromConsist(String string);
}
