package com.zuk.rest.news;

import com.zuk.dto.news.NewsDto;
import com.zuk.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;


@RestController
@RequestMapping(value = "/api/v1/news/")
public class NewsControllerV1 {

    private final NewsService newsService;


    @Autowired
    public NewsControllerV1(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity findById(@PathVariable(name = "id") Long id){

        return new ResponseEntity<>(NewsDto.fromAdminNews(newsService.findById(id)), HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity findAll(){

        return new ResponseEntity<>(NewsDto.fromAdminArrayNews(newsService.findAll()), HttpStatus.OK);
    }

    @GetMapping(value = "getNew/")
    public ResponseEntity getNew(){
        return new ResponseEntity<>(NewsDto.fromArrayNews(newsService.findAllAfter(new Timestamp(System.currentTimeMillis()))), HttpStatus.OK);
    }
}
