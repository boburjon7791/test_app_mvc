package com.example.test.services;

import com.example.test.dtos.StartTest;
import org.springframework.stereotype.Service;

import com.example.test.dtos.QuestionCreateDto;
import com.example.test.dtos.QuestionGetDto;
import com.example.test.dtos.QuestionUpdateDto;
import java.util.List;

@Service
public interface QuestionService {
    QuestionGetDto create(QuestionCreateDto dto);
    QuestionGetDto get(Long id);
    QuestionGetDto update(QuestionUpdateDto dto);
    List<QuestionGetDto> questions();
    List<QuestionGetDto> questionsByName(String name);
    StartTest startTest(StartTest startTest);
    void deleteQuestion(Long id);
}
