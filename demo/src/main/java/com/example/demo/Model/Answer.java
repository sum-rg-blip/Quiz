package com.example.demo.Model;



import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chosenAnswer; // e.g., "B"

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    // getters and setters
}

