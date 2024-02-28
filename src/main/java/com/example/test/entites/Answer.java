package com.example.test.entites;

import org.springframework.stereotype.Indexed;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "answer",
   indexes = {
   @Index(name = "text_of_answer",columnList = "text")
   }
)
@Getter
@Builder
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false)
    private String text;

    @ToString.Exclude
    @OneToOne(mappedBy = "answer")
    private Question question;

    @ToString.Exclude
    @OneToOne(mappedBy = "variant1")
    private Question question1;

    @ToString.Exclude
    @OneToOne(mappedBy = "variant2")
    private Question question2;
    
    @ToString.Exclude
    @OneToOne(mappedBy = "variant3")
    private Question question3;
}
