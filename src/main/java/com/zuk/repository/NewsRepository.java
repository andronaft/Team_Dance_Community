package com.zuk.repository;

import com.zuk.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByDataAfter(Timestamp date);
    List<News> findByDataAfterAndDataBefore(Timestamp start, Timestamp end);
}
