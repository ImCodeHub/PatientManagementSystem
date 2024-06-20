package com.Patient.PatientManagementSystem.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Patient.PatientManagementSystem.Entity.Patient;
import com.Patient.PatientManagementSystem.Model.PatientModel;

@Service
public interface PatientService {
    public String savePatient(Patient patient);
    public List<PatientModel> getAllPatient();
    public List<PatientModel> getPatient(Long id);
    public Boolean updatePatient(Long id, PatientModel patientModel);
    public Boolean deletePatient(Long id);
}
