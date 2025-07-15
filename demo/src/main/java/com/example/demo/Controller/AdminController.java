package com.example.demo.Controller;



import com.example.demo.Model.Question;
import com.example.demo.Model.Quiz;
import com.example.demo.Service.QuestionService;
import com.example.demo.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private QuizService quizService;
    @Autowired private QuestionService questionService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("quizzes", quizService.findAll());
        return "adminDashboard";
    }

    @GetMapping("/quiz/new")
    public String createQuizForm(Model model) {
        model.addAttribute("quiz", new Quiz());
        return "quizForm";
    }

    @PostMapping("/quiz/save")
    public String saveQuiz(@ModelAttribute Quiz quiz) {
        quizService.save(quiz);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/quiz/{id}/questions")
    public String manageQuestions(@PathVariable Long id, Model model) {
        var quiz = quizService.findById(id).orElseThrow();
        var questions = questionService.findByQuizId(id);
        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", questions);
        return "questionsPage";
    }

    @GetMapping("/question/new/{quizId}")
    public String createQuestionForm(@PathVariable Long quizId, Model model) {
        var question = new Question();
        question.setQuiz(quizService.findById(quizId).orElseThrow());
        model.addAttribute("question", question);
        return "questionForm";
    }

    @PostMapping("/question/save")
    public String saveQuestion(@ModelAttribute Question question) {
        questionService.save(question);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/quiz/delete/{id}")
    public String deleteQuiz(@PathVariable Long id) {
        quizService.deleteById(id);
        return "redirect:/admin/dashboard";
    }
}

