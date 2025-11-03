package com.example.news.controllers;

import com.example.news.dto.NewDto;
import com.example.news.exceptions.NewValidationException;
import com.example.news.models.New;
import com.example.news.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<New> getNewById(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.getNewById(id));
    }

    @PostMapping
    public ResponseEntity<New> createNew(@RequestBody(required = false) NewDto newDto) {
        if (newDto == null) {
            throw new NewValidationException("Данные новости не предоставлены");
        }
        New createdNew = newsService.createNew(newDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<New> updateNew(@PathVariable Long id,
                                         @RequestBody(required = false) NewDto newDto) {
        if (newDto == null) {
            throw new NewValidationException("Данные новости не предоставлены");
        }
        New updatedNew = newsService.updateNew(id, newDto);
        return ResponseEntity.ok(updatedNew);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNewById(@PathVariable Long id) {
        newsService.deleteNewById(id);
        return ResponseEntity.noContent().build();
    }
}
