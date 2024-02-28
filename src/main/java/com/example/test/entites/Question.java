package com.example.test.entites;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "question")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(unique = true,nullable = false)
    private String text;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Answer answer;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Answer variant1;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Answer variant2;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Answer variant3;
}
