package com.zuk.service.impl;

import com.zuk.model.News;
import com.zuk.model.Status;
import com.zuk.repository.NewsRepository;
import com.zuk.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;


@Service
@Slf4j
public class NewsServiceImpl implements NewsService{
    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }


    @Override
    public ArrayList<News> findAllAfter(Timestamp date) {
        return (ArrayList<News>) newsRepository.findByDataAfter(date);
    }

    @Override
    public ArrayList<News> findAllByDataAfterAndDataBefore(Timestamp start, Timestamp end) {
        return (ArrayList<News>) newsRepository.findByDataAfterAndDataBefore(start,end);
    }

    @Override
    public ArrayList<News> findAll() {
        return (ArrayList<News>) newsRepository.findAll();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.getOne(id);
    }

    @Override
    public News create(News news) {
        news.setStatus(Status.ACTIVE);
        return newsRepository.save(news);
    }

    @Override
    public News updated(News news) {
        News findNews = newsRepository.getOne(news.getId());
        news.setCreated(findNews.getCreated());
        return newsRepository.save(news);
    }
}
