package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  Id;
    private String name;
    private Integer age;
    private String gender;
    private String phone;
    private String email;
    private String address;
    @Column(length = 1000)
    private String medicalHistory;
}