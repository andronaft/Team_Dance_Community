package com.zuk.dto.news;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zuk.model.News;
import com.zuk.model.Status;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsDto {

    private Long id;
    private String name;
    private String description;
    private String text;
    private String imgUrl;
    private Timestamp data;
    private Status status;
    private Timestamp created;
    private Timestamp updated;

    public News toNews(){
        News news = new News();
        news.setId(id);
        news.setName(name);
        news.setDescription(description);
        news.setText(text);
        news.setImgUrl(imgUrl);
        news.setData(data);
        return news;
    }

    public News toNewsForCreate(){
        News news = new News();
        //news.setId(id);
        news.setName(name);
        news.setDescription(description);
        news.setText(text);
        //news.setImgUrl(imgUrl);
        news.setData(data);
        return news;
    }
    public News toNewsWithStatus(){
        News news = new News();
        news.setId(id);
        news.setName(name);
        news.setDescription(description);
        news.setText(text);
        news.setImgUrl(imgUrl);
        news.setData(data);
        news.setStatus(status);
        return news;
    }

    public News toAdminNews(){
        News news = new News();
        news.setId(id);
        news.setName(name);
        news.setDescription(description);
        news.setText(text);
        news.setImgUrl(imgUrl);
        news.setData(data);
        news.setCreated(created);
        news.setUpdated(updated);
        news.setStatus(status);
        return news;
    }

    public static NewsDto fromNews(News news){
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setName(news.getName());
        newsDto.setDescription(news.getDescription());
        newsDto.setText(news.getText());
        newsDto.setImgUrl(news.getImgUrl());
        newsDto.setData(news.getData());
        return newsDto;
    }

    public static NewsDto fromNewsWithStatus(News news){
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setName(news.getName());
        newsDto.setDescription(news.getDescription());
        newsDto.setText(news.getText());
        newsDto.setImgUrl(news.getImgUrl());
        newsDto.setData(news.getData());
        newsDto.setStatus(news.getStatus());
        return newsDto;
    }

    public static NewsDto fromAdminNews(News news){
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setName(news.getName());
        newsDto.setDescription(news.getDescription());
        newsDto.setText(news.getText());
        newsDto.setImgUrl(news.getImgUrl());
        newsDto.setData(news.getData());
        newsDto.setStatus(news.getStatus());
        newsDto.setCreated(news.getCreated());
        newsDto.setUpdated(news.getUpdated());
        return newsDto;
    }

    public static ArrayList<NewsDto> fromArrayNews(List<News> listNews){
        ArrayList<NewsDto> arrayListNewsDto = new ArrayList<>();
        for(News news: listNews){
            arrayListNewsDto.add(fromNews(news));
        }
        return arrayListNewsDto;
    }

    public static ArrayList<NewsDto> fromArrayNewsWithStatus(List<News> listNews){
        ArrayList<NewsDto> arrayListNewsDto = new ArrayList<>();
        for(News news: listNews){
            arrayListNewsDto.add(fromNewsWithStatus(news));
        }
        return arrayListNewsDto;
    }

    public static ArrayList<NewsDto> fromAdminArrayNews(List<News> listNews){
        ArrayList<NewsDto> arrayListNewsDto = new ArrayList<>();
        for(News news: listNews){
            arrayListNewsDto.add(fromAdminNews(news));
        }
        return arrayListNewsDto;
    }

}
