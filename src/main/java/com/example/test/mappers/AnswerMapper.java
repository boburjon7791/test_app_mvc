package com.example.test.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.test.dtos.AnswerDto;
import com.example.test.entites.Answer;

@Component
@Mapper(componentModel = "spring")
public interface AnswerMapper {
    AnswerDto toDto(Answer answer);
    Answer toEntity(AnswerDto dto);
}
