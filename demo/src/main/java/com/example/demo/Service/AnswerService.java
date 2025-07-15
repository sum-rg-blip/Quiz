package com.example.demo.Service;



import com.example.demo.Model.Answer;
import com.example.demo.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> findByStudentId(Long studentId) {
        return answerRepository.findByStudentId(studentId);
    }

    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }
}
