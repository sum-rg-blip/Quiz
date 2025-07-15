package com.example.demo.Model;



import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role; // e.g., ROLE_ADMIN, ROLE_STUDENT

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Student student;

    // getters and setters
}

