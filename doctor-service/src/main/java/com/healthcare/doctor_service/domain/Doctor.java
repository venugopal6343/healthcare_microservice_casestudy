package com.healthcare.doctor_service.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Structure of the Doctor document
 * {
 *     "_id": 1,
 *     "fullName": "Dr. John Doe",
 *     "email": "john.doe@hospital.com",
 *     "phone": "123-456-7890",
 *     "address": "123 Main St, City, State, 12345",
 *     "specialization": ["Cardiology", "Neurology"],
 *     "op_days": ["Monday", "Wednesday", "Friday"],
 * }
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
@Document(collection = "doctors")
public class Doctor {

    @Id
    private String id;

    private String fullName;

    private String email;

    private String phone;

    private String address;

    private String[] specialization;

    @Field("op_days")
    private String[] opDays;

    public Doctor(String fullName, String email, String phone, String address,
                  String[] specialization, String[] opDays) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.specialization = specialization;
        this.opDays = opDays;
    }
}
