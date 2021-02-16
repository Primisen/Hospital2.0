package training.nadia.hospital.service;

import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.Treatment;
import training.nadia.hospital.exception.ServiceException;

public interface DoctorService {

    void setDiagnosisAndTreatment(String diagnosis, Treatment treatment, Patient patient, Doctor doctor) throws ServiceException;

    void identifyPatients(Doctor doctor) throws ServiceException;

    void identifyPatientsWhoNeedToBeCheckup(Doctor doctor) throws ServiceException;

    void dischargePatient(Patient patient) throws ServiceException;
}
