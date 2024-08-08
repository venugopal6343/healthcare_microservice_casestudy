package com.healthcare.patient_service;

import com.healthcare.patient_service.domain.Patient;
import com.healthcare.patient_service.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient createPatient(Patient patient) {
        log.debug("Creating patient: {}", patient);
        patientRepository
                .findByEmailOrPhone(patient.getEmail(), patient.getPhone())
                .ifPresent(p -> {
                    log.error("Patient already exists with email: {} or phone: {}", p.getEmail(), p.getPhone());
                    throw new RuntimeException("Patient already exists with email: " +
                            p.getEmail() + " or phone: " + p.getPhone());
                });
        log.debug("Patient does not exist with email: {} or phone: {}", patient.getEmail(), patient.getPhone());
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatient(long id) {
        log.debug("Getting patient, id: {}", id);
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found, id: " + id));
    }

    @Override
    public Patient updatePatient(Patient patient) {
        log.debug("Updating patient: {}", patient);
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(long id) {
        log.debug("Deleting patient, id: {}", id);
        patientRepository.deleteById(id);
    }

    @Override
    public Optional<Patient> searchByEmail(String email) {
        log.debug("Searching patient by email: {}", email);
        return patientRepository.searchByEmail(email);
    }

    @Override
    public Patient searchByPhone(String phone) {
        log.debug("Searching patient by phone: {}", phone);
        return patientRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Patient not found, phone: " + phone));
    }

    @Override
    public List<Patient> getAllPatients() {
        log.debug("Getting all patients");
        return List.copyOf(patientRepository.findAll());
    }
}
