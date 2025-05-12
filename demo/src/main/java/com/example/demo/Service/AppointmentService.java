package com.example.demo.Service;

import com.example.demo.Model.Appointment;
import com.example.demo.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Optional<Appointment> updateAppointment(Long id, Appointment appointment) {
        return appointmentRepository.findById(id)
                .map(existingAppointment -> {
                    appointment.setId(id);
                    return appointmentRepository.save(appointment);
                });
    }

    public boolean deleteAppointment(Long id) {
        return appointmentRepository.findById(id)
                .map(appointment -> {
                    appointmentRepository.delete(appointment);
                    return true;
                })
                .orElse(false);
    }
}
