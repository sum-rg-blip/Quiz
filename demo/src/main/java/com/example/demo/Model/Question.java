package com.example.demo.Model;



import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctAnswer; // e.g., "A"

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    // getters and setters
}

