package com.example.demo.Service;



import com.example.demo.Model.Question;
import com.example.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> findByQuizId(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }

    public Question save(Question question) {
        return questionRepository.save(question);
    }
}

