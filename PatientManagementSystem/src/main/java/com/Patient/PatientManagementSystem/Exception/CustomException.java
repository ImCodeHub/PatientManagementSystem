package com.Patient.PatientManagementSystem.Exception;

public class CustomException {

    public static class PatientNotFoundException extends RuntimeException {

        public PatientNotFoundException(String message) {
            super(message);
        }

    }

    public static class InvalidEmailException extends RuntimeException {
        public InvalidEmailException(String message) {
            super(message);
        }
    }

    public static class EmailAlreadyExistException extends RuntimeException {
        public EmailAlreadyExistException(String message) {
            super(message);
        }
    }
}
