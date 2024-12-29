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
            model.setContent(entity.getContent());
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
        newsModel.setContent(news.getContent());
        return newsModel;


    }
    
    public NewsModel addNews(String title, String content, String location, String type, String picture) {
        // Create a new NewsEntity
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setDetail(title);
        newsEntity.setContent(content);
        newsEntity.setLocation(location);
        newsEntity.setType(type);
        newsEntity.setPicture(picture);


        // Save the entity to the database
        NewsEntity savedEntity = newsRepository.save(newsEntity);

        // Map the saved entity to a NewsModel
        NewsModel savedModel = new NewsModel();
        savedModel.setId(String.valueOf(savedEntity.getId()));
        savedModel.setDetail(savedEntity.getDetail());
        savedModel.setContent(savedEntity.getContent());
        savedModel.setLocation(savedEntity.getLocation());
        savedModel.setDate(savedEntity.getDate());
        savedModel.setPicture(savedEntity.getPicture());
        
        return savedModel;
    }
}
