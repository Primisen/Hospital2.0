package training.nadia.hospital.dao;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.entity.Nurse;
import training.nadia.hospital.entity.Patient;

public interface NurseDao {

    void identifyNursePatients(Nurse nurse) throws DaoException;

    void updateNumberOfCompletedTherapies(Patient patient, int numberOfTherapies) throws DaoException;
}
