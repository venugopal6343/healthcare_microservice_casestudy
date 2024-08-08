package com.healthcare.patient_service;

import com.healthcare.patient_service.domain.Patient;
import com.healthcare.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient createPatient(Patient patient) {
        patientRepository
                .findByEmailOrPhone(patient.getEmail(), patient.getPhone())
                .ifPresent(p -> {
                    throw new RuntimeException("Patient already exists with email: " +
                            p.getEmail() + " or phone: " + p.getPhone());
                });
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatient(long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found, id: " + id));
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Optional<Patient> searchByEmail(String email) {
        return patientRepository.searchByEmail(email);
    }

    @Override
    public Patient searchByPhone(String phone) {
        return patientRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Patient not found, phone: " + phone));
    }

    @Override
    public List<Patient> getAllPatients() {
        return List.copyOf(patientRepository.findAll());
    }
}
