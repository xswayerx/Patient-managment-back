package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("Id")
    private Long id;

    private String name;
    private Integer age;
    private String gender;
    private String phone;
    private String email;
    private String address;

    @Column(length = 1000)
    private String medicalHistory;

    private LocalDate rendezvous;
}