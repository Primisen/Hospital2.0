package training.nadia.hospital.dao;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.Treatment;

import java.util.List;

public interface DoctorDao {

    void setDiagnosis(String diagnosis, long patientId) throws DaoException;

    void setTreatment(Treatment treatment, long patientId) throws DaoException;

    void identifyPatients(Doctor doctor) throws DaoException;
}
