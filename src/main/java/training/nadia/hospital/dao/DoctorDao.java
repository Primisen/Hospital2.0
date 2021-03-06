package training.nadia.hospital.dao;

import training.nadia.hospital.exception.DaoException;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;

public interface DoctorDao {

    void setDiagnosisAndTreatment(Patient patient) throws DaoException;

    void getPatients(Doctor doctor) throws DaoException;

    void getReceivingPatients(Doctor doctor) throws DaoException;

    void dischargePatient(Patient patient) throws DaoException;
}
