package com.zuk.service;

import com.zuk.model.News;

import java.sql.Timestamp;
import java.util.ArrayList;


public interface NewsService {
    ArrayList<News> findAllAfter(Timestamp date);
    ArrayList<News> findAllByDataAfterAndDataBefore(Timestamp start, Timestamp end);

    ArrayList<News> findAll();
    News findById(Long id);

    News create(News news);
    News updated(News news);
}
