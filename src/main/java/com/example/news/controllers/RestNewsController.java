package com.example.news.controllers;

import com.example.news.exceptions.NewNotFoundException;
import com.example.news.models.New;
import com.example.news.repos.NewsRepo;
import com.example.news.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public New getNewById(@PathVariable Long id) {
        return newsService.getNewById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNewById(@PathVariable Long id) {
        newsService.deleteNewById(id);
        return ResponseEntity.ok("Новость с ID " + id + " успешно удалена");
    }
}
