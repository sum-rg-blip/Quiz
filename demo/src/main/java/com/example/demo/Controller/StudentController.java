package com.example.demo.Controller;



import com.example.demo.Model.*;
import com.example.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired private StudentService studentService;
    @Autowired private QuizService quizService;
    @Autowired private QuestionService questionService;
    @Autowired private ResultService resultService;
    @Autowired private AnswerService answerService;

    // student enters studentId to join quiz room
    @GetMapping("/room")
    public String studentRoom() {
        return "studentRoom"; // page to input studentId
    }

    @PostMapping("/join")
    public String joinQuiz(@RequestParam String studentId, Model model) {
        var studentOpt = studentService.findByStudentId(studentId);
        if (studentOpt.isEmpty()) {
            model.addAttribute("error", "Invalid student ID");
            return "studentRoom";
        }
        var quizzes = quizService.findAll();
        model.addAttribute("student", studentOpt.get());
        model.addAttribute("quizzes", quizzes);
        return "quizPage"; // show list of quizzes to take
    }

    @GetMapping("/take/{quizId}/{studentId}")
    public String takeQuiz(@PathVariable Long quizId, @PathVariable String studentId, Model model) {
        var questions = questionService.findByQuizId(quizId);
        model.addAttribute("questions", questions);
        model.addAttribute("quizId", quizId);
        model.addAttribute("studentId", studentId);
        return "quizPage"; // page shows quiz questions
    }

    @PostMapping("/submit")
    public String submitQuiz(
            @RequestParam Long quizId,
            @RequestParam String studentId,
            @RequestParam Map<String, String> answers,
            Model model
    ) {
        var student = studentService.findByStudentId(studentId).orElseThrow();
        var quiz = quizService.findById(quizId).orElseThrow();
        var questions = questionService.findByQuizId(quizId);

        int score = 0;
        for (var question : questions) {
            String key = "question_" + question.getId();
            String chosenAnswer = answers.get(key);
            if (chosenAnswer != null && chosenAnswer.equals(question.getCorrectAnswer())) {
                score++;
            }
            // save answer
            Answer answer = new Answer();
            answer.setStudent(student);
            answer.setQuestion(question);
            answer.setChosenAnswer(chosenAnswer);
            answerService.save(answer);
        }

        // save result
        StudentResult result = new StudentResult();
        result.setStudent(student);
        result.setQuiz(quiz);
        result.setScore(score);
        result.setTakenAt(LocalDateTime.now());
        resultService.save(result);

        model.addAttribute("score", score);
        model.addAttribute("total", questions.size());
        return "studentResult"; // show result
    }

    @GetMapping("/answers/{studentId}")
    public String viewAnswers(@PathVariable String studentId, Model model) {
        var student = studentService.findByStudentId(studentId).orElseThrow();
        var answers = answerService.findByStudentId(student.getId());
        model.addAttribute("answers", answers);
        return "viewQuestions"; // show questions + correct answers + chosen
    }
}

