package com.Patient.PatientManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Patient.PatientManagementSystem.Entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
