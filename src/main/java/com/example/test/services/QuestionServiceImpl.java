package com.example.test.services;

import com.example.test.dtos.QuestionCreateDto;
import com.example.test.dtos.QuestionGetDto;
import com.example.test.dtos.QuestionUpdateDto;
import com.example.test.dtos.StartTest;
import com.example.test.entites.Answer;
import com.example.test.entites.Question;
import com.example.test.exceptions.BadRequestException;
import com.example.test.exceptions.NotFoundException;
import com.example.test.repositories.AnswerDao;
import com.example.test.repositories.QuestionDao;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;
    private final AnswerDao answerDao;
    @Override
    @Transactional
    public QuestionGetDto create(QuestionCreateDto dto) {
       if (questionDao.existsByText(dto.text())) {
          throw new BadRequestException("Ushbu savol matni avvaldan mavjud");
       }
        Answer answer = answerDao.save(Answer.builder().text(dto.answer()).build());
        Answer variant1 = answerDao.save(Answer.builder().text(dto.variant1()).build());
        Answer variant2 = answerDao.save(Answer.builder().text(dto.variant2()).build());
        Answer variant3 = answerDao.save(Answer.builder().text(dto.variant3()).build());
        Question question = Question.builder()
                            .answer(answer)
                            .text(dto.text())
                            .variant1(variant1)
                            .variant2(variant2)
                            .variant3(variant3)
                            .build();
        Question saved = questionDao.save(question);
        return update(saved);
    }

    @Override
    @Transactional
    public QuestionGetDto get(Long id) {
        Question question = questionDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Savol topilmadi"));
        return update(question);
    }

    @Override
    @Transactional
    public QuestionGetDto update(QuestionUpdateDto dto) {
        if (questionDao.existsByText(dto.text(),dto.id())) {
            throw new BadRequestException("Ushbu savol matni avvaldan mavjud");
        }
        Question question = questionDao.findById(dto.id())
                .orElseThrow(() -> new NotFoundException("Savol topilmadi"));
        Answer answer = answerDao.findById(dto.answerId())
                .orElseThrow(() -> new NotFoundException("Javob topilmadi"));
                Answer variant1 = answerDao.findById(dto.variantOneId())
                .orElseThrow(() -> new NotFoundException("Javob topilmadi"));
                Answer variant2 = answerDao.findById(dto.variantTwoId())
                .orElseThrow(() -> new NotFoundException("Javob topilmadi"));
                Answer variant3 = answerDao.findById(dto.variantThreeId())
                .orElseThrow(() -> new NotFoundException("Javob topilmadi"));
        answer.setText(dto.answer());
        question.setText(dto.text());
        question.setAnswer(answer);

        variant1.setText(dto.variant1());
        question.setVariant1(variant1);
        
        variant2.setText(dto.variant2());
        question.setVariant2(variant2);
        
        variant3.setText(dto.variant3());
        question.setVariant3(variant3);
        
        Question saved = questionDao.save(question);
        return update(saved);
    }
    public QuestionGetDto update(Question question){
        Map<Long,String> ids=new LinkedHashMap<>(Map.of(
              question.getAnswer().getId(),question.getAnswer().getText(),
              question.getVariant1().getId(),question.getVariant1().getText(),
              question.getVariant2().getId(),question.getVariant2().getText(),
              question.getVariant3().getId(),question.getVariant3().getText()
        ));

        List<Long> allIds=new ArrayList<>(ids.keySet());

        Collections.shuffle(allIds);

        Map<Long,String> variants=new LinkedHashMap<>();

        allIds.forEach(id -> variants.put(id, ids.get(id)));

        return QuestionGetDto.builder()
               .id(question.getId())
               .text(question.getText())
               .answerId(question.getAnswer().getId())
               .answer(question.getAnswer().getText())
               .variantOneId(question.getVariant1().getId())
               .variant1(question.getVariant1().getText())
               .variantTwoId(question.getVariant2().getId())
               .variant2(question.getVariant2().getText())
               .variantThreeId(question.getVariant3().getId())
               .variant3(question.getVariant3().getText())
               .variants(variants)
               .build();
    }

    @Override
    public List<QuestionGetDto> questions() {
        List<Question> questions = questionDao.findAll();
        List<QuestionGetDto> questionGetDtoList = questions.stream()
                .map(this::update)
                .collect(Collectors.toList());
        Collections.shuffle(questionGetDtoList);
        return questionGetDtoList;
    }

    @Override
    public List<QuestionGetDto> questionsByName(String name) {
        List<Question> questions = questionDao.findByContainsName(name);
        List<QuestionGetDto> questionGetDtoList = questions.stream()
                .map(this::update)
                .collect(Collectors.toList());
        Collections.shuffle(questionGetDtoList);
        return questionGetDtoList;
    }

    @Override
    public StartTest startTest(StartTest startTest) {
        if (!questionDao.existsById(startTest.questionId())) {
            throw new NotFoundException("Savol topilmadi");
        }
        if (!answerDao.existsById(startTest.answerId())) {
            throw new NotFoundException("Javob topilmadi");
        }
        Question question = questionDao.findById(startTest.questionId())
                .orElseThrow(() -> new NotFoundException("Savol topilmadi"));
        if (question.getAnswer().getId().equals(startTest.answerId())) {
            return new StartTest(
                    startTest.questionId(),
                    startTest.answerId(),
                    "Javobingiz to'g'ri"
            );
        }
        return new StartTest(
                startTest.questionId(),
                question.getAnswer().getId(),
                "Javobingiz noto'g'ri"
        );
    }

    @Override
    @Transactional
    public void deleteQuestion(@NonNull Long id) {
        Question question=questionDao.findById(id)
                   .orElseThrow(() -> new NotFoundException("Savol topilmadi"));
        answerDao.deleteById(question.getAnswer().getId());
        answerDao.deleteById(question.getVariant1().getId());
        answerDao.deleteById(question.getVariant2().getId());
        answerDao.deleteById(question.getVariant3().getId());
        questionDao.deleteById(id);
    }

}
