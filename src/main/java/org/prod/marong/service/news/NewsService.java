package org.prod.marong.service.news;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.prod.marong.model.NewsModel;

import org.prod.marong.model.NewsResponseModel;
import org.prod.marong.model.entity.NewsEntity;
import org.prod.marong.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;
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
            model.setTitle(entity.getTitle());
            model.setLocation(entity.getLocation()); // Static or derived location
            model.setDate(entity.getDate());
            model.setContent(entity.getContent());
            model.setType(entity.getType());
            return model;
        }).collect(Collectors.toList());

        return newsData;
    }

    public NewsModel getNewsById(String id) {
        NewsEntity news = newsRepository.findNewsById(id);
        NewsModel newsModel = new NewsModel();
        newsModel.setId(String.valueOf(news.getId()));
        newsModel.setTitle(news.getTitle());
        newsModel.setLocation(news.getLocation());
        newsModel.setDate(news.getDate());
        newsModel.setPicture(news.getPicture());
        newsModel.setContent(news.getContent());
        newsModel.setType(news.getType());
        return newsModel;


    }

    public NewsEntity addNews(String title, String content, String location, String type, String picture) {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTitle(title);
        newsEntity.setContent(content);
        newsEntity.setLocation(location);
        newsEntity.setType(type);
        newsEntity.setPicture(picture);
        newsEntity.setDate(LocalDate.now());

        NewsEntity savedEntity = newsRepository.save(newsEntity);
        return savedEntity;
    }


    public NewsEntity deleteNewsById(String id) {
        NewsEntity news = newsRepository.findNewsById(id);
        newsRepository.delete(news);
        return news;
    }

    public NewsResponseModel editNewsById(String id, String title, String content, String location, String type, String picture) {
        NewsEntity existingNews = newsRepository.findNewsById(id);
        if (existingNews == null) {
            throw new IllegalArgumentException("News with id " + id + " not found");
        }
        if (title != null && !title.isEmpty()) {
            existingNews.setTitle(title);
        }
        if (content != null && !content.isEmpty()) {
            existingNews.setContent(content);
        }
        if (location != null && !location.isEmpty()) {
            existingNews.setLocation(location);
        }
        if (type != null && !type.isEmpty()) {
            existingNews.setType(type);
        }
        if (picture != null && !picture.isEmpty()) {
            existingNews.setPicture(picture);
        }

        NewsEntity updatedNews = newsRepository.save(existingNews);
        
        NewsResponseModel newsModel = new NewsResponseModel();
        newsModel.setId(updatedNews.getId().toString());
        newsModel.setTitle(updatedNews.getTitle());
        newsModel.setLocation(updatedNews.getLocation());
        newsModel.setDate(updatedNews.getDate());
        newsModel.setPicture(updatedNews.getPicture());
        newsModel.setContent(updatedNews.getContent());
        newsModel.setType(updatedNews.getType());
        newsModel.setLast_updated(String.valueOf(LocalDate.now()));

        return newsModel;
    }
}
