package com.example.demo.Service;


import com.example.demo.Model.StudentResult;
import com.example.demo.repository.StudentResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    @Autowired
    private StudentResultRepository studentResultRepository;

    public List<StudentResult> findByStudentId(Long studentId) {
        return studentResultRepository.findByStudentId(studentId);
    }

    public StudentResult save(StudentResult result) {
        return studentResultRepository.save(result);
    }
}
