package com.example.news.controllers;

import com.example.news.models.New;
import com.example.news.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
public class RestNewsController {
    private final NewsService newsService;

    @GetMapping
    public List<New> getNews() {
        return newsService.getAllNews();
    }

}
