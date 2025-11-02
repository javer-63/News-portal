package com.example.news.services;

import com.example.news.models.New;
import com.example.news.repos.NewsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsService {
    private final NewsRepo newsRepo;

    public List<New> getAllNews() {
        log.info("Получение всех новостей");
        return newsRepo.findAll();
    }

}
