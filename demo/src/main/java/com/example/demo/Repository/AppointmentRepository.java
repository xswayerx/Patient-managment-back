package com.example.demo.Repository;

import com.example.demo.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  AppointmentRepository extends JpaRepository<Appointment, Long> {
}