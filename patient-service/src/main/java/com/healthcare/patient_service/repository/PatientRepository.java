package com.healthcare.patient_service.repository;

import com.healthcare.patient_service.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE p.email LIKE %?1%")
    Optional<Patient> searchByEmail(String email);

    Optional<Patient> findByPhone(String phone);

    Optional<Patient> findByEmailOrPhone(String email, String phone);

}
