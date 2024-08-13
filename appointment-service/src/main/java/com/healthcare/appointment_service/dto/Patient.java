package com.healthcare.appointment_service.dto;

public record Patient(
        long id,
        String fullName,
        String phone
) {
}
