package com.citiustech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citiustech.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
