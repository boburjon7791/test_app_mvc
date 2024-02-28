package com.example.test.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.test.entites.Answer;

import java.util.List;

@Repository
public interface AnswerDao extends JpaRepository<Answer, Long>{

    @Query(value = "from Answer a where a.id not in (:ids)")
    List<Answer> findByNotInIds(@Param("ids") List<Long> ids, Pageable pageable);

    @Query(value = "from Answer a where upper(a.text) like upper(concat('%',?1,'%'))")
    List<Answer> findByTextContains(String name);
    
    boolean existsByText(String text);
    
    @Query(value = "select exists(from Answer a where a.text=?1 and a.id<>?2)")
    boolean existsByText(String text,Long id);
}
