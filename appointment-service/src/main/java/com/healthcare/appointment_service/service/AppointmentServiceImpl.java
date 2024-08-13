package com.healthcare.appointment_service.service;

import com.healthcare.appointment_service.domain.Appointment;
import com.healthcare.appointment_service.dto.Doctor;
import com.healthcare.appointment_service.dto.Patient;
import com.healthcare.appointment_service.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public Appointment getAppointmentById(String id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Appointment> findAllByPatientId(String patientId) {
        return appointmentRepository.findAllByPatientId(patientId);
    }

    @Override
    public List<Appointment> findAllByDoctorId(String doctorId) {
        return appointmentRepository.findAllByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> findAllByPatientIdAndDoctorId(String patientId, String doctorId) {
        return appointmentRepository.findAllByPatientIdAndDoctorId(patientId, doctorId);
    }

    @Override
    public Patient getPatientById(String patientId) {
        return null;
    }

    @Override
    public Doctor getDoctorById(String doctorId) {
        return null;
    }
}
