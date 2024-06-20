package com.Patient.PatientManagementSystem.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.Patient.PatientManagementSystem.Entity.Patient;
import com.Patient.PatientManagementSystem.Model.PatientModel;
import com.Patient.PatientManagementSystem.Service.implement.PatientServiceImpl;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1")
public class PatientController {

    Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientServiceImpl patientServiceImpl;

    @PostMapping("/savePatient")
    public ResponseEntity<String> postMethodName(@RequestBody Patient patient) {
        logger.info("try to save details");
        String response = patientServiceImpl.savePatient(patient);
        logger.info("details has been saved.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientModel>> allPatient() {
        List<PatientModel> list = patientServiceImpl.getAllPatient();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<List<PatientModel>> getPatient(@PathVariable("id") Long id) {
        List<PatientModel> list = patientServiceImpl.getPatient(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updatePatientById(@PathVariable("id") Long id,
            @RequestBody PatientModel patientModel) {
        Boolean response = patientServiceImpl.updatePatient(id, patientModel);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deletePatient(@PathVariable("id") Long id) {
        Boolean response = patientServiceImpl.deletePatient(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
