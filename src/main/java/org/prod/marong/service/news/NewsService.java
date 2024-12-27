package org.prod.marong.service.news;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.prod.marong.model.NewsModel;

import org.prod.marong.model.entity.NewsEntity;
import org.prod.marong.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {

    @Autowired
    NewsRepository newsRepository;
    public List<NewsModel> getAllNews() {
        List<NewsEntity> allNews = newsRepository.findAllNews();

        // Map entities to DTOs
        List<NewsModel> newsData = allNews.stream().map(entity -> {
            NewsModel model = new NewsModel();
            model.setId(entity.getId().toString());
            model.setPicture(entity.getPicture());
            model.setDetail(entity.getDetail());
            model.setLocation(entity.getLocation()); // Static or derived location
            model.setDate(entity.getDate());
            return model;
        }).collect(Collectors.toList());

        return newsData;
    }

    public NewsModel getNewsById(String id) {
        NewsEntity news = newsRepository.findNewsById(id);
        NewsModel newsModel = new NewsModel();
        newsModel.setId(String.valueOf(news.getId()));
        newsModel.setDetail(news.getDetail());
        newsModel.setLocation(news.getLocation());
        newsModel.setDate(news.getDate());
        newsModel.setPicture(news.getPicture());
        return newsModel;


    }
}
