package com.teleconsulting.demo.service;

import com.teleconsulting.demo.model.Doctor;
import com.teleconsulting.demo.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    private DoctorRepository doctorRepository;


    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor findByPhoneNumber(String phoneNumber) {
        return doctorRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Doctor updateDoctorIncomingCall(String doctorPhoneNumber, String patientPhoneNumber) {
        Doctor doctor = doctorRepository.findByPhoneNumber(doctorPhoneNumber);
        if (doctor != null) {
            doctor.setIncomingCall(patientPhoneNumber);
            return doctorRepository.save(doctor);
        } else {
            return null; // Handle doctor not found scenario
        }
    }
    @Override
    public Doctor findById(Long id) {
        return doctorRepository.findById(id).orElse(null); // Implement findById method
    }

}
