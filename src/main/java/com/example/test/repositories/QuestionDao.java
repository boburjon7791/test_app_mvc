package com.example.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.test.entites.Question;

import jakarta.transaction.Transactional;
import lombok.NonNull;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Long>{
    @Query(value = "from Question q where upper(q.text) like upper(concat('%',?1,'%'))")
    List<Question> findByContainsName(String name);

    @Query(value = "select exists (from Question q where q.id=?1 and q.answer.id=?2)")
    boolean startTest(Long questionId, Long answerId);

    boolean existsByText(String text);

    @Query(value = "select exists(from Question q where q.text=?1 and q.id<>?2)")
    boolean existsByText(String text,Long id);

    @Modifying
    @Transactional
    @Query(value="delete Question q where q.id=?1")
    void deleteQuestionById(@NonNull Long id);
}
