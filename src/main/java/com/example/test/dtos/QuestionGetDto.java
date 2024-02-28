package com.example.test.dtos;

import java.util.Map;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class QuestionGetDto{
    Long id;
    String text;
    Long answerId;
    String answer;
    Long variantOneId;
    String variant1;
    Long variantTwoId;
    String variant2;
    Long variantThreeId;
    String variant3;
    Map<Long, String> variants;
}
