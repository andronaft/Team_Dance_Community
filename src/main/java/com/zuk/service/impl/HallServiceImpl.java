package com.zuk.service.impl;

import com.zuk.model.Hall;
import com.zuk.repository.HallRepository;
import com.zuk.service.HallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;

    @Autowired
    public HallServiceImpl(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public Hall findByName(String name) {
        return hallRepository.findByName(name);
    }

    @Override
    public Hall findById(Long id) {
        return hallRepository.getOne(id);
    }

    @Override
    public List<Hall> findAll() {
        return hallRepository.findAll();
    }
}
