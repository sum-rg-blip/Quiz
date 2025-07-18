package com.example.demo.repository;


import com.example.demo.Model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByStudentId(Long studentId);
}
