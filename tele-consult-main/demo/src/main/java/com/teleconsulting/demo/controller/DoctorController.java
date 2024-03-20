package com.teleconsulting.demo.controller;

import com.teleconsulting.demo.exception.UserNotFoundException;
import com.teleconsulting.demo.model.Doctor;
import com.teleconsulting.demo.repository.DoctorRepository;
import com.teleconsulting.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.*;



@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping("/add")
    public String add(@RequestBody Doctor doctor)
    {
        doctorService.saveDoctor(doctor);
        return "New Doctor Added";
    }

    // "localhost:8081/doctor/"
    @GetMapping("/doctor")
    List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    @DeleteMapping("/doctor/{id}")
    String deleteDoctor(@PathVariable Long id){
       if(!doctorRepository.existsById(id)){
            throw new UserNotFoundException(id);
      }
        doctorRepository.deleteById(id);
        return  "Doctor with id "+id+" has been deleted success.";
    }
    @GetMapping("/doctor/{id}")
    Doctor getUserById(@PathVariable Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
@PutMapping("/doctors/{id}")
Doctor updateDoctor(@RequestBody Doctor newDoctor, @PathVariable Long id) {
    return doctorRepository.findById(id)
            .map(Doctor -> {
                Doctor.setGender(newDoctor.getGender());
                Doctor.setName(newDoctor.getName());
                Doctor.setPhoneNumber(newDoctor.getPhoneNumber());
                Doctor.setStatus(newDoctor.getStatus());
                return doctorRepository.save(Doctor);
            }).orElseThrow(() -> new UserNotFoundException(id));
}
}
