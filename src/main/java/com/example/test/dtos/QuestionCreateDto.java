package com.example.test.dtos;

import jakarta.validation.constraints.NotBlank;

public record QuestionCreateDto(
    @NotBlank
    String text,
    
    @NotBlank
    String answer,
    
    @NotBlank
    String variant1,
    
    @NotBlank
    String variant2,
    
    @NotBlank
    String variant3
) {}
