package com.healthcare.patient_service.controller;

import com.healthcare.patient_service.domain.Patient;
import com.healthcare.patient_service.dto.PatientDto;
import com.healthcare.patient_service.service.PatientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/patients")
@Slf4j
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@Valid @RequestBody PatientDto dto) {
        // Create a new patient
        Patient patient = toEntity(dto);
        // Save the patient to the database
        patient = patientService.createPatient(patient);
        dto = toDto(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    // handle runtime exception
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        log.error("An error occurred: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    // Handle spring validation exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e) {
        log.error("An error occurred: ", e);
        var fieldErrors = e.getFieldErrors();
        Map<Object, Object> errors = new HashMap<>();
        fieldErrors.forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    private PatientDto toDto(Patient patient) {
        return new PatientDto(patient.getId(), patient.getFullName(),
                patient.getEmail(), patient.getPhone(), patient.getAddress(), patient.getDob());
    }

    private Patient toEntity(PatientDto dto) {
        Patient patient = new Patient();
        patient.setFullName(dto.fullName());
        patient.setEmail(dto.email());
        patient.setPhone(dto.phone());
        patient.setAddress(dto.address());
        patient.setDob(dto.dob());
        return patient;
    }
}
