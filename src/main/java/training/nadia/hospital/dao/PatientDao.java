package training.nadia.hospital.dao;

import training.nadia.hospital.exception.DaoException;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;

public interface PatientDao {

    void setTreatingDoctor(Patient patient, Doctor doctor) throws DaoException;

    void getTreatmentData(Patient patient) throws DaoException;
}
