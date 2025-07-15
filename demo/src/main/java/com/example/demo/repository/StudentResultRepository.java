package com.example.demo.repository;



import com.example.demo.Model.StudentResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentResultRepository extends JpaRepository<StudentResult, Long> {
    List<StudentResult> findByStudentId(Long studentId);
}

