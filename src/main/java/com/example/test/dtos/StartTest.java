package com.example.test.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record StartTest(
   @NotNull
   @PositiveOrZero
   Long questionId,

   @NotNull
   @PositiveOrZero
   Long answerId,

   String description
) {}
