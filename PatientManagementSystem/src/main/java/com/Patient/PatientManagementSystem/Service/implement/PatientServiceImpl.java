package com.Patient.PatientManagementSystem.Service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Patient.PatientManagementSystem.Entity.Patient;
import com.Patient.PatientManagementSystem.Model.PatientModel;
import com.Patient.PatientManagementSystem.Repository.PatientRepository;
import com.Patient.PatientManagementSystem.Service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

    Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public String savePatient(Patient patient) {
        logger.info("patient saved {}", patient);
        patientRepository.save(patient);
        return "patient saved successfully.";
    }

    @Override
    public List<PatientModel> getAllPatient() {
        List<PatientModel> list = new ArrayList<>();
        List<Patient> patients = patientRepository.findAll();
        if (!patients.isEmpty()) {

            for (Patient patient : patients) {
                PatientModel patientModel = new PatientModel();

                patientModel.setId(patient.getId());
                patientModel.setFirstName(patient.getFirstName());
                patientModel.setLastName(patient.getLastName());
                patientModel.setDateOfBirth(patient.getDateOfBirth());
                patientModel.setGender(patient.getGender());
                patientModel.setContactNumber(patient.getContactNumber());
                patientModel.setEmail(patient.getEmail());
                patientModel.setAddress(patient.getAddress());
                
                // add in the List of PatientModel line 34.
                list.add(patientModel);
            }
            return list;
        }
        throw new UnsupportedOperationException("Unimplemented method 'getAllPatient'");
    }

    @Override
    public List<PatientModel> getPatient(Long id) {
        List<PatientModel> list = new ArrayList<>();
        Optional<Patient> optional = patientRepository.findById(id);
        if (optional.isPresent()) {
            Patient patient = optional.get();
            PatientModel patientModel = new PatientModel();
            patientModel.setId(patient.getId());
            patientModel.setFirstName(patient.getFirstName());
            patientModel.setLastName(patient.getLastName());
            patientModel.setDateOfBirth(patient.getDateOfBirth());
            patientModel.setGender(patient.getGender());
            patientModel.setContactNumber(patient.getContactNumber());
            patientModel.setEmail(patient.getEmail());
            patientModel.setAddress(patient.getAddress());

            list.add(patientModel);
            return list;
        } else {

            throw new UnsupportedOperationException("Unimplemented method 'getPatient'");
        }
    }

    @Override
    public Boolean updatePatient(Long id, PatientModel patientModel) {
        Optional<Patient> optional = patientRepository.findById(id);
        if (optional.isPresent()) {
            Patient patient = optional.get();
            logger.info("patient details to change {}", patientModel.getFirstName());

            patient.setFirstName(patientModel.getFirstName());
            patient.setLastName(patientModel.getLastName());
            patient.setDateOfBirth(patientModel.getDateOfBirth());
            patient.setGender(patientModel.getGender());
            patient.setContactNumber(patientModel.getContactNumber());
            patient.setEmail(patientModel.getEmail());
            patient.setAddress(patientModel.getAddress());

            patientRepository.save(patient);
            return true;
        } else {
            throw new UnsupportedOperationException("Unimplemented method 'updatePatient'");
        }
    }

    @Override
    public Boolean deletePatient(Long id) {
        Optional<Patient> optional = patientRepository.findById(id);
        if (optional.isPresent()) {
            logger.info("patient has been deleted for this id -{}",id);
            patientRepository.deleteById(id);
            return true;
        } else {
            throw new UnsupportedOperationException("Unimplemented method 'deletePatient'");
        }
    }

}
