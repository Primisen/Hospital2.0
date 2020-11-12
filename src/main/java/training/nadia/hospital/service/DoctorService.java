package training.nadia.hospital.service;

import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.Treatment;
import training.nadia.hospital.service.exception.ServiceException;

public interface DoctorService {

    void setDiagnosisAndTreatment(String diagnosis, Treatment treatment, Patient patient, Doctor doctor) throws ServiceException;

    void getPatients(Doctor doctor) throws ServiceException;

    void getReceivingPatients(Doctor doctor) throws ServiceException;

    void dischargePatient(Patient patient) throws ServiceException;
}
