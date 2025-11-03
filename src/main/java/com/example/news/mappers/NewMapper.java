package com.example.news.mappers;

import com.example.news.dto.NewDto;
import com.example.news.models.New;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NewMapper {

    public New dtoToNew(NewDto newDto) {
        if (newDto == null) return null;
        New aNew = new New();
        aNew.setTitle(newDto.getTitle());
        aNew.setDescription(newDto.getDescription());
        aNew.setContent(newDto.getContent());
        aNew.setCreatedAt(LocalDateTime.now());
        return aNew;
    }

    public NewDto newToDto(New aNew) {
        if (aNew == null) return null;
        NewDto newDto = new NewDto();
        newDto.setTitle(aNew.getTitle());
        newDto.setDescription(aNew.getDescription());
        newDto.setContent(aNew.getContent());

        return newDto;
    }
}