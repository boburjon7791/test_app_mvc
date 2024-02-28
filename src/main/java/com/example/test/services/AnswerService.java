package com.example.test.services;

import org.springframework.stereotype.Service;

import com.example.test.dtos.AnswerDto;
import java.util.List;

@Service
public interface AnswerService {
    AnswerDto create(AnswerDto dto);
    AnswerDto create(String text);
    AnswerDto get(Long id);
    AnswerDto update(AnswerDto dto);
    List<AnswerDto> answers(List<Long> ids);
    List<AnswerDto> answersByName(String name);

    List<AnswerDto> answers();
}
