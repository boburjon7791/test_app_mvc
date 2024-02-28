package com.example.test;

import org.mapstruct.factory.Mappers;

import com.example.test.dtos.AnswerDto;
import com.example.test.dtos.QuestionCreateDto;
import com.example.test.dtos.QuestionGetDto;
import com.example.test.dtos.QuestionUpdateDto;
import com.example.test.entites.Answer;
import com.example.test.entites.Question;
import com.example.test.mappers.AnswerMapper;
import com.example.test.mappers.QuestionMapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Test {
    private static final QuestionMapper questionMapper=null;
    private static final AnswerMapper answerMapper=null;
    public static void main(String[] args) {
        /*QuestionCreateDto createDto=new QuestionCreateDto("q1", "a1");
        System.out.println("createDto.toString() = " + createDto.toString());
        Question question=questionMapper.toEntity(createDto);
        System.out.println("question = " + question);

        QuestionGetDto getDto=questionMapper.toDto(question);
        System.out.println("getDto.toString() = " + getDto.toString());

        QuestionUpdateDto updateDto=new QuestionUpdateDto(1l, "q2", 1l, "a2");
        System.out.println("updateDto.toString() = " + updateDto.toString());
        Question question2=questionMapper.toEntity(updateDto);
        System.out.println("question2 = " + question2);
        System.out.println();
        System.out.println();
        System.out.println();
        AnswerDto answerDto=new AnswerDto(1l,"a3");
        System.out.println("answerDto.toString() = " + answerDto.toString());
        Answer answer=answerMapper.toEntity(answerDto);
        System.out.println("answer = "+answer);
        AnswerDto answerDto2= answerMapper.toDto(answer);
        System.out.println("answerDto2.toString() = " + answerDto2.toString());*/
    }
}
