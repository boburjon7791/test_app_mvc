package com.example.test.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record QuestionUpdateDto(
    @NotNull
    @PositiveOrZero
    Long id,

    @NotBlank
    String text,

    @NotNull
    @PositiveOrZero
    Long answerId,

    @NotBlank
    String answer,

    @NotNull
    @PositiveOrZero
    Long variantOneId,

    @NotNull
    String variant1,

    @NotNull
    @PositiveOrZero
    Long variantTwoId,

    @NotBlank
    String variant2,

    @NotNull
    @PositiveOrZero
    Long variantThreeId,

    @NotBlank
    String variant3
) {}
