package com.example.demo.Controller;

import com.example.demo.DTO.AppointmentDTO;
import com.example.demo.Model.Appointment;
import com.example.demo.Model.Patient;
import com.example.demo.Service.AppointmentService;
import com.example.demo.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") Long id) {
        return appointmentService.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        Patient patient = patientRepository.findById(appointmentDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Invalid patient selected. Please select a valid patient."));
        Appointment appointment = new Appointment();
        appointment.setDate(appointmentDTO.getDate());
        appointment.setTime(appointmentDTO.getTime());
        appointment.setDuration(appointmentDTO.getDuration());
        appointment.setType(appointmentDTO.getType());
        appointment.setStatus(appointmentDTO.getStatus());
        appointment.setPatient(patient);
        Appointment saved = appointmentService.saveAppointment(appointment);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable("id") Long id, @RequestBody AppointmentDTO appointmentDTO) {
        Patient patient = patientRepository.findById(appointmentDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Invalid patient selected. Please select a valid patient."));
        Appointment appointment = new Appointment();
        appointment.setId(id);
        appointment.setDate(appointmentDTO.getDate());
        appointment.setTime(appointmentDTO.getTime());
        appointment.setDuration(appointmentDTO.getDuration());
        appointment.setType(appointmentDTO.getType());
        appointment.setStatus(appointmentDTO.getStatus());
        appointment.setPatient(patient);
        Optional<Appointment> updated = appointmentService.updateAppointment(id, appointment);
        return updated.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable("id") Long id) {
        return appointmentService.deleteAppointment(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

//    @GetMapping("/patient-name/{patientId}")
//    public ResponseEntity<String> getPatientName(@PathVariable Long patientId) {
//        String name = appointmentService.getPatientNameBy(patientId);
//        return ResponseEntity.ok(name);
//    }

}