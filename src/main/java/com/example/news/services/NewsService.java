package com.example.news.services;

import com.example.news.dto.NewDto;
import com.example.news.exceptions.NewNotFoundException;
import com.example.news.exceptions.NewCreationException;
import com.example.news.mappers.NewMapper;
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
    private final NewMapper newMapper;

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
    public void createNew(NewDto newDto) {
        if (newDto.getTitle() == null || newDto.getTitle().trim().isEmpty()) {
            throw new NewCreationException("Заголовок не может быть пустым");
        }
        if (newDto.getDescription() == null || newDto.getDescription().trim().isEmpty()) {
            throw new NewCreationException("Описание не может быть пустым");
        }
        if (newDto.getContent() == null || newDto.getContent().trim().isEmpty()) {
            throw new NewCreationException("Содержание не может быть пустым");
        }
        newsRepo.save(newMapper.dtoToNew(newDto));
        log.info("Новость '{}' успешно создана", newDto.getTitle());
    }

    @Transactional
    public void deleteNewById(Long id) {
        newsRepo.delete(findNewOrThrow(id));
        log.info("Новость с ID {} успешно удалена", id);
    }
}
