package com.example.news.mappers;

import com.example.news.dto.NewDto;
import com.example.news.models.New;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NewMapper {

    public New dtoToNew(NewDto newDto) {
        if (newDto == null) {
            return null;
        }

        New aNew = new New();
        aNew.setTitle(newDto.getTitle());
        aNew.setDescription(newDto.getDescription());
        aNew.setContent(newDto.getContent());
        aNew.setCreatedAt(LocalDateTime.now());

        return aNew;
    }

    public NewDto newToDto(New news) {
        if (news == null) {
            return null;
        }

        NewDto newDto = new NewDto();
        newDto.setTitle(news.getTitle());
        newDto.setDescription(news.getDescription());
        newDto.setContent(news.getContent());

        return newDto;
    }
}