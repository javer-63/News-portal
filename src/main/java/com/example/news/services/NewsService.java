package com.example.news.services;

import com.example.news.dto.NewDto;
import com.example.news.exceptions.NewNotFoundException;
import com.example.news.exceptions.NewValidationException;
import com.example.news.mappers.NewMapper;
import com.example.news.models.New;
import com.example.news.repos.NewsRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    private void validateNewDto(NewDto newDto) {
        if (newDto.getTitle() == null || newDto.getTitle().trim().isEmpty()) {
            throw new NewValidationException("Заголовок не может быть пустым");
        }
        if (newDto.getDescription() == null || newDto.getDescription().trim().isEmpty()) {
            throw new NewValidationException("Описание не может быть пустым");
        }
        if (newDto.getContent() == null || newDto.getContent().trim().isEmpty()) {
            throw new NewValidationException("Содержание не может быть пустым");
        }
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
    public New createNew(NewDto newDto) {
        validateNewDto(newDto);
        New aNew = newsRepo.save(newMapper.dtoToNew(newDto));
        log.info("Новость '{}' успешно создана", newDto.getTitle());
        return aNew;
    }

    @Transactional
    public New updateNew(Long id, NewDto newDto) {
        validateNewDto(newDto);
        New aNew = findNewOrThrow(id);
        newMapper.updateNewFromDto(aNew, newDto);
        New updatedNew = newsRepo.save(aNew);
        log.info("Новость с ID {} успешно обновлена", id);
        return updatedNew;
    }

    @Transactional
    public New partUpdateNew(Long id, NewDto newUpdates) {
        New aNew = findNewOrThrow(id);
        Optional.ofNullable(newUpdates.getTitle()).ifPresent(aNew::setTitle);
        Optional.ofNullable(newUpdates.getDescription()).ifPresent(aNew::setDescription);
        Optional.ofNullable(newUpdates.getContent()).ifPresent(aNew::setContent);
        New updatedNew = newsRepo.save(aNew);
        log.info("Новость с ID {} частично обновлена", id);
        return updatedNew;
    }
    @Transactional
    public void deleteNewById(Long id) {
        newsRepo.delete(findNewOrThrow(id));
        log.info("Новость с ID {} успешно удалена", id);
    }
}
