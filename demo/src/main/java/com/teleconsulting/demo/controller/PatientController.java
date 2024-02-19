package com.teleconsulting.demo.controller;

import com.teleconsulting.demo.model.Patient;
import com.teleconsulting.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/add")
    public String add(@RequestBody Patient patient)
    {
        patientService.savePatient(patient);
        return "New Patient Added";
    }
}
