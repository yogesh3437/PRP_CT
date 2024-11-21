package com.citiustech.cotroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.citiustech.model.Patient;
import com.citiustech.service.PatientService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/register")
    public String showPatientRegistrationPage(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient-registration";
    }

    @PostMapping("/register")
    public String registerPatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "patient-registration";
        }

        patientService.registerPatient(patient);
        model.addAttribute("message", "Patient registration successful!");
        return "patient-registration";
    }

    @GetMapping("/dashboard")
    public String patientDashboard() {
        return "patient-dashboard";
    }
}

