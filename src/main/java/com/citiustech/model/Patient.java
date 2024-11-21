package com.citiustech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required.")
    @Size(max = 50, message = "Name must not exceed 50 characters.")
    private String name;

    @Min(value = 0, message = "Age must be a positive number.")
    @Max(value = 120, message = "Age must be realistic.")
    private int age;

    @Size(max = 500, message = "Medical history must not exceed 500 characters.")
    private String medicalHistory;

    @OneToOne
    private User user;

    // Getters and Setters
}
