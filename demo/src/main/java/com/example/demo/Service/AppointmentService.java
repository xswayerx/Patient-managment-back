package com.example.demo.Service;

import com.example.demo.Model.Appointment;
import com.example.demo.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Repository.PatientRepository;
import java.util.List;
import java.util.Optional;
import com.example.demo.Model.Patient;

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

    @Autowired
    private PatientRepository patientRepository;

    public Appointment saveAppointment(Appointment appointment) {
        if (appointment.getPatient() != null && appointment.getPatient().getId() != null) {
            Patient patient = patientRepository.findById(appointment.getPatient().getId())
                    .orElseThrow(() -> new RuntimeException("Invalid patient selected. Please select a valid patient."));
            appointment.setPatient(patient);
        } else {
            throw new RuntimeException("Invalid patient selected. Please select a valid patient.");
        }
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
//    public String getPatientNameBy(Long patientId) {
//        return patientRepository.findById(patientId)
//                .map(Patient::getName)
//                .orElse("Unknown Patient");
//    }
}
