package com.example.demo.Service;

import com.example.demo.Model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Optional<Student> findByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }
}

