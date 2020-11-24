package training.nadia.hospital.service;

import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.exception.ServiceException;

public interface PatientService {

    void goToTheDoctor(Patient patient, Doctor doctor) throws ServiceException;

    void getTreatmentData(Patient patient) throws ServiceException;
}
