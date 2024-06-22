package com.Patient.PatientManagementSystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.Patient.PatientManagementSystem.Exception.CustomException.EmailAlreadyExistException;
import com.Patient.PatientManagementSystem.Exception.CustomException.InvalidEmailException;
import com.Patient.PatientManagementSystem.Exception.CustomException.PatientNotFoundException;

@ControllerAdvice
public class PatientExceptionHandler {
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<?> handlePatientNotFoundException(PatientNotFoundException exception, WebRequest request) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<?> handleInvalidEmailException(InvalidEmailException exception, WebRequest request) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<?> handleEmailAlreadyExistException(EmailAlreadyExistException excption, WebRequest request) {
        return new ResponseEntity<>(excption.getMessage(), HttpStatus.CONFLICT);
    }
}
