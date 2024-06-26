package com.Patient.PatientManagementSystem.Service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Patient.PatientManagementSystem.Entity.Patient;
import com.Patient.PatientManagementSystem.Exception.CustomException.EmailAlreadyExistException;
import com.Patient.PatientManagementSystem.Exception.CustomException.InvalidEmailException;
import com.Patient.PatientManagementSystem.Exception.CustomException.PatientNotFoundException;
import com.Patient.PatientManagementSystem.Model.PatientModel;
import com.Patient.PatientManagementSystem.Repository.PatientRepository;
import com.Patient.PatientManagementSystem.Service.PatientService;
import com.Patient.PatientManagementSystem.Service.Validation.Validator;

@Service
public class PatientServiceImpl implements PatientService {

    Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private Validator validator;

    @Override
    public String savePatient(Patient patient) {
        String email = patient.getEmail();

        if(!validator.isValidEmail(email)){
            logger.error("Email format is not valid for : {}",email);
            throw new InvalidEmailException("Email is invalid kinldy Enter valid E-mail id");
        }
        if(!validator.isEmailUnique(email)){
            logger.error("email is already exist: {}", email);
            throw new EmailAlreadyExistException("Email is already exist");
        }
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
        throw new PatientNotFoundException("Patient Not found/Empty");
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

            throw new PatientNotFoundException("Patient Not found/Empty by this id: "+id);
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
            throw new PatientNotFoundException("Patient Not found/Empty by this id: "+id);
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
            throw new PatientNotFoundException("Patient Not found/Empty by this id: "+id);
        }
    }

}
