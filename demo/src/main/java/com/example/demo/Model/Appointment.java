package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Transient
    private Long patientId;
    private LocalDate date;
    private LocalTime time;
    private Integer duration;
    private String type;
    private String status;

    @PostLoad
    private void populateTransientFieldsFromPatient() {
        if (patient != null) {
            this.patientId = patient.getId();
        }
    }
}