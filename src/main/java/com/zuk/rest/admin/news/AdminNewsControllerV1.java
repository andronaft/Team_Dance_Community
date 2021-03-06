package com.zuk.rest.admin.news;

import com.zuk.dto.news.NewsDto;
import com.zuk.dto.training.GroupTrainingDto;
import com.zuk.model.News;
import com.zuk.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin/news/")
public class AdminNewsControllerV1 {
    private final NewsService newsService;


    @Autowired
    public AdminNewsControllerV1(NewsService newsService) {
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

    @PostMapping(value = "create/")
    public ResponseEntity create(@RequestBody NewsDto newsDto){
        return new ResponseEntity<>(NewsDto.fromAdminNews(newsService.create(newsDto.toNewsForCreate())), HttpStatus.OK);
    }

    @PostMapping(value = "update/")
    public ResponseEntity update(@RequestBody NewsDto newsDto){
        return new ResponseEntity<>(NewsDto.fromAdminNews(newsService.updated(newsDto.toNewsWithStatus())), HttpStatus.OK);
    }
}
