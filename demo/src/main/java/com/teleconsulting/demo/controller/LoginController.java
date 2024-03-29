package com.teleconsulting.demo.controller;

import com.teleconsulting.demo.model.Doctor;
import com.teleconsulting.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final DoctorService doctorService;

    @Autowired
    public LoginController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Doctor doctor) {
        // Get doctor by phone number from the database
        Doctor existingDoctor = doctorService.findByPhoneNumber(doctor.getPhoneNumber());

        // Check if doctor exists and the password matches
        if (existingDoctor != null && existingDoctor.getPassword().equals(doctor.getPassword())) {
            // Assuming you have a getter method for getting doctor ID
            Long doctorId = existingDoctor.getId();
            return ResponseEntity.ok().body(Map.of("doctorId", doctorId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid phone number or password");
        }
    }
}
