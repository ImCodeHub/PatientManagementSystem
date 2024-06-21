package com.Patient.PatientManagementSystem.Service.Validation;

import org.slf4j.LoggerFactory;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Patient.PatientManagementSystem.Entity.Patient;
import com.Patient.PatientManagementSystem.Repository.PatientRepository;

@Service
public class Validator {
    private Logger logger = LoggerFactory.getLogger(Validator.class);

    @Autowired
    private PatientRepository patientRepository;

    public boolean isValidEmail(String email) {

        String emailRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+$";
        boolean isValid = email.matches(emailRegex);
        logger.debug("check email : {} is valid : {}", email, isValid);
        return isValid;
    }

    public boolean isEmailUnique(String email){
        Optional<Patient> optional=patientRepository.findByEmail(email);
        boolean isUnique = optional.isEmpty();
        logger.debug("the email: {} is unique: {}",email,isUnique);
        return isUnique;
    }

}
