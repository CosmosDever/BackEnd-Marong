package org.prod.marong.controller;


import org.modelmapper.ModelMapper;
import org.prod.marong.model.NewsModel;
import org.prod.marong.model.NewsResponseModel;
import org.prod.marong.model.ResponseModel;
import org.prod.marong.model.UserModel;
import org.prod.marong.model.entity.NewsEntity;
import org.prod.marong.service.TestService;
import org.prod.marong.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class NewsController {

    private static final String SUCCESS = "200";
    private static final String ERROR = "400";

    @Autowired
    private TestService testService;

    @Autowired
    ModelMapper mapper;

    @Autowired
    NewsService newsService;


    @GetMapping("/api/News/all")
    public ResponseModel getAllNews(){
        try {
            List<NewsModel> newsList = newsService.getAllNews();
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("All data retrieved successfully")
                    .data(newsList)
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("Error retrieving data: " + e.getMessage())
                    .build();
        }
    }

    @GetMapping("/api/News/{id}")
    public ResponseModel getNewsById(@PathVariable("id") String id){
        try {
            NewsModel newsList = newsService.getNewsById(id);
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("All data retrieved successfully")
                    .data(newsList)
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("Error retrieving data: " + e.getMessage())
                    .build();
        }
    }

    @PostMapping("/api/News/addNews")
    @PreAuthorize("hasRole('ROLE_Admin') or hasRole('ROLE_master Admin')")
    public ResponseModel addNews( @RequestParam("title") String title,
                                  @RequestParam("content") String content,
                                  @RequestParam("location") String location,
                                  @RequestParam("type") String type,
                                  @RequestParam("picture") String picture ){
        try {
            NewsEntity newsList = newsService.addNews(title, content, location, type, picture);
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("All data retrieved successfully")
                    .data(newsList)
                    .build();

        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("Error Add news: " + e.getMessage())
                    .build();
        }
    }

    @DeleteMapping("/api/News/{id}/Delete")
    @PreAuthorize("hasRole('ROLE_Admin') or hasRole('ROLE_master Admin')")
    public ResponseModel deleteNews(@PathVariable("id") String id){
        try {
            NewsEntity newsList = newsService.deleteNewsById(id);
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("News article deleted successfully.")
                    .data(newsList)
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("News article with the specified ID was not found: " + e.getMessage())
                    .build();
        }
    }

    @PatchMapping("/api/News/{id}/Edit")
    @PreAuthorize("hasRole('ROLE_Admin') or hasRole('ROLE_master Admin')")
    public ResponseModel editNews( @PathVariable("id") String id,
                                   @RequestParam("title") String title,
                                   @RequestParam("content") String content,
                                   @RequestParam("location") String location,
                                   @RequestParam("type") String type,
                                   @RequestParam("picture") String picture
                                   ){
        try {
            NewsResponseModel newsList = newsService.editNewsById(id,title, content, location, type, picture);
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("All data retrieved successfully")
                    .data(newsList)
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("Error Add news: " + e.getMessage())
                    .build();
        }
    }

}
