package com.example.demo.Model;



import jakarta.persistence.*;
import lombok.Data;


import java.util.List;
@Data
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentId; // e.g., "STU2025001"
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "student")
    private List<StudentResult> results;

    @OneToMany(mappedBy = "student")
    private List<Answer> answers;

    // getters and setters
}
