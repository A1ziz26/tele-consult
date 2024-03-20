package com.teleconsulting.demo.repository;

import com.teleconsulting.demo.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
