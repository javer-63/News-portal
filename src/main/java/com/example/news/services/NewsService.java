package com.example.news.services;

import com.example.news.exceptions.NewNotFoundException;
import com.example.news.models.New;
import com.example.news.repos.NewsRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsService {
    private final NewsRepo newsRepo;

    private New findNewOrThrow(Long id) {
        return newsRepo.findById(id)
                .orElseThrow(() -> {
                    log.warn("Новость с ID {} не найдена", id);
                    return new NewNotFoundException(id);
                });
    }

    public List<New> getAllNews() {
        log.info("Получение всех новостей");
        return newsRepo.findAll();
    }

    public New getNewById(Long id) {
        New aNew = findNewOrThrow(id);
        log.info("Новость с ID {} успешно найдена", id);
        return aNew;
    }

    @Transactional
    public void deleteNewById(Long id) {
        newsRepo.delete(findNewOrThrow(id));
        log.info("Новость с ID {} успешно удалена", id);
    }
}
