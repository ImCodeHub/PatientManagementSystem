package com.Patient.PatientManagementSystem.Service.implement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Patient.PatientManagementSystem.Entity.Patient;
import com.Patient.PatientManagementSystem.Exception.CustomException.EmailAlreadyExistException;
import com.Patient.PatientManagementSystem.Exception.CustomException.InvalidEmailException;
import com.Patient.PatientManagementSystem.Exception.CustomException.PatientNotFoundException;
import com.Patient.PatientManagementSystem.Model.PatientModel;
import com.Patient.PatientManagementSystem.Repository.PatientRepository;
import com.Patient.PatientManagementSystem.Service.Validation.Validator;

public class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private PatientServiceImpl patientServiceImpl;

    Patient patient;

    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        patient = new Patient();
        patient.setFirstName("Ankit");
        patient.setLastName("sharma");
        patient.setDateOfBirth(LocalDate.of(1993, 11, 24));
        patient.setGender("male");
        patient.setContactNumber("1236547894");
        patient.setEmail("ankit@gmail.com");
        patient.setAddress("Indore");

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testSavePatient() {
        String email = patient.getEmail();

        when(validator.isValidEmail(email)).thenReturn(true);
        when(validator.isEmailUnique(email)).thenReturn(true);
        when(patientRepository.save(patient)).thenReturn(patient);

        String result = patientServiceImpl.savePatient(patient);
        assertEquals("patient saved successfully.", result);
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    void testSavePatient_InvalidEmail(){
        String email = patient.getEmail();
        when(validator.isValidEmail(email)).thenReturn(false);

        InvalidEmailException exception = assertThrows(InvalidEmailException.class, () -> {
            patientServiceImpl.savePatient(patient);
        });
        assertEquals("Email is invalid kinldy Enter valid E-mail id", exception.getMessage());
        verify(patientRepository, times(0)).save(any(Patient.class));
    }

    @Test
    void testSavePatient_EmailAlreadyExist(){
        String email = patient.getEmail();
        when(validator.isValidEmail(email)).thenReturn(true);
        when(validator.isEmailUnique(email)).thenReturn(false);

        EmailAlreadyExistException exception = assertThrows(EmailAlreadyExistException.class, ()->{
            patientServiceImpl.savePatient(patient);
        });

        assertEquals("Email is already exist", exception.getMessage());
        verify(patientRepository,times(0)).save(any(Patient.class));
    }

    @Test
    void testGetAllPatient_Success() {
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        when(patientRepository.findAll()).thenReturn(patients);

        List<PatientModel> result = patientServiceImpl.getAllPatient();
        assertEquals(patient.getId(), result.get(0).getId());
        verify(patientRepository,times(1)).findAll();
    }

    @Test
    void testGetPatient_Found() {
        Long patientId = 1L;

        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));

        List<PatientModel> result = patientServiceImpl.getPatient(patientId);
        PatientModel patientModel = result.get(0);
        assertEquals(patientId, result.size() );
        assertEquals(patient.getId(), patientModel.getId());
        assertEquals(patient.getFirstName(), patientModel.getFirstName());
        assertEquals(patient.getLastName(), patientModel.getLastName());
        assertEquals(patient.getDateOfBirth(), patientModel.getDateOfBirth());
        assertEquals(patient.getGender(), patientModel.getGender());
        assertEquals(patient.getContactNumber(), patientModel.getContactNumber());
        assertEquals(patient.getEmail(), patientModel.getEmail());
        assertEquals(patient.getAddress(), patientModel.getAddress());

        verify(patientRepository, times(1)).findById(patientId);
    } 

    @Test
    void testGetPatient_NotFound(){
        Long patientId = 1L;

        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        PatientNotFoundException exception = assertThrows(PatientNotFoundException.class, () ->{
            patientServiceImpl.getPatient(patientId);
        });

        assertEquals("Patient Not found/Empty by this id: "+patientId, exception.getMessage());

        verify(patientRepository,times(1)).findById(patientId);
    }

    @Test
    void testUpdatePatient() {
        Long patientId = 1L;
        PatientModel patientModel = new PatientModel();
        patientModel.setFirstName("abhishek");

        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));

        Boolean result = patientServiceImpl.updatePatient(patientId, patientModel);

        assertEquals(true, result);
        assertEquals("abhishek", patient.getFirstName());

        verify(patientRepository, times(1)).findById(patientId);
        verify(patientRepository,times(1)).save(patient);

    }

    @Test 
    void testUpdatePatient_NotFound(){
        Long patientId = 1L;

        PatientModel patientModel = new PatientModel();
        patientModel.setFirstName("abhishek");

        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        PatientNotFoundException exception = assertThrows(PatientNotFoundException.class, () ->{
            patientServiceImpl.updatePatient(patientId, patientModel);
        });

        assertEquals("Patient Not found/Empty by this id: "+patientId, exception.getMessage());
        verify(patientRepository, times(1)).findById(patientId);
        verify(patientRepository,times(0)).save(any(Patient.class));
    }

    @Test
    void testDeletePatient_Found(){
        Long patientId = 1L;
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));
        Boolean result = patientServiceImpl.deletePatient(patientId);
        assertEquals(true, result);
        verify(patientRepository,times(1)).findById(patientId);
    }

    @Test
    void testDeletePatient_NotFound(){
        Long patientId = 1L;

        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        PatientNotFoundException exception = assertThrows(PatientNotFoundException.class, () ->{
            patientServiceImpl.deletePatient(patientId);
        });

        assertEquals("Patient Not found/Empty by this id: "+patientId, exception.getMessage());

    }

    

}
