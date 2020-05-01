package com.zuk.service;

import com.zuk.model.Hall;

import java.util.List;

public interface HallService {
    Hall findByName(String name);
    Hall findById(Long id);
    List<Hall> findAll();
}
