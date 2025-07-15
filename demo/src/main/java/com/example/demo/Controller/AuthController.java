package com.example.demo.Controller;



import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "studentRoom"; // login.html Thymeleaf page
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    // optionally register page for demo
    @GetMapping("/register")
    public String register() {
        return "register"; // register.html if you implement
    }
    UserService userService;
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {

        userService.registerUser(user);
        return "redirect:/login?registered"; // or a success page
    }

}

