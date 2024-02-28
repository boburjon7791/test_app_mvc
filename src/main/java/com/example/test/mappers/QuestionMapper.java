package com.example.test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.test.dtos.QuestionCreateDto;
import com.example.test.dtos.QuestionGetDto;
import com.example.test.dtos.QuestionUpdateDto;
import com.example.test.entites.Question;

@Component
// @Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "answer",ignore = true)
    @Mapping(target = "answer",ignore = true)
    Question toEntity(QuestionCreateDto dto);

    @Mapping(target = "answer",ignore = true)
    Question toEntity(QuestionUpdateDto dto);

    @Mapping(target = "answers",ignore = true)    
    @Mapping(target = "rightAnswerText",ignore = true)
    @Mapping(target = "rightAnswerId",ignore = true)
    QuestionGetDto toDto(Question question);
}
