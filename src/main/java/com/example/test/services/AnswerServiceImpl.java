package com.example.test.services;

import com.example.test.dtos.AnswerDto;
import com.example.test.entites.Answer;
import com.example.test.exceptions.BadRequestException;
import com.example.test.exceptions.NotFoundException;
import com.example.test.mappers.AnswerMapper;
import com.example.test.repositories.AnswerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerDao answerDao;
    private final AnswerMapper answerMapper;
    @Override
    public AnswerDto create(AnswerDto dto) {
        if (answerDao.existsByText(dto.text())) {
            throw new BadRequestException("Ushbu javob matni avvaldan mavjud");
        }
        Answer answer = answerMapper.toEntity(dto);
        Answer saved = answerDao.save(answer);
        return answerMapper.toDto(saved);
    }

    @Override
    public AnswerDto create(String text) {
        if (answerDao.existsByText(text)) {
            throw new BadRequestException("Ushbu javob matni avvaldan mavjud");

        }
        Answer saved = answerDao.save(Answer.builder().text(text).build());
        return answerMapper.toDto(saved);
    }

    @Override
    public AnswerDto get(Long id) {
        if (!answerDao.existsById(id)) {
            throw new NotFoundException("Ushbu javob topilmadi");
        }
        Answer answer = answerDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Ushbu javob topilmadi"));
        return answerMapper.toDto(answer);
    }

    @Override
    public AnswerDto update(AnswerDto dto) {
        if (!answerDao.existsById(dto.id())) {
            throw new NotFoundException("Ushbu javob topilmadi");
        }
        if (answerDao.existsByText(dto.text(),dto.id())) {
            throw new BadRequestException("Ushbu javob matni avvaldan mavjud");
        }
        Answer answer = answerMapper.toEntity(dto);
        Answer updated = answerDao.save(answer);
        return answerMapper.toDto(updated);
    }

    @Override
    public List<AnswerDto> answers(List<Long> ids) {
        List<Answer> answers=answerDao.findByNotInIds(ids, PageRequest.of(0,3));
        return answers.stream().map(answerMapper::toDto).toList();
    }

    @Override
    public List<AnswerDto> answersByName(String name) {
        List<Answer> answers=answerDao.findByTextContains(name);
        return answers.stream().map(answerMapper::toDto).toList();
    }

    @Override
    public List<AnswerDto> answers() {
        List<Answer> answers = answerDao.findAll();
        return answers.stream()
                .map(answerMapper::toDto)
                .toList();
    }
}
