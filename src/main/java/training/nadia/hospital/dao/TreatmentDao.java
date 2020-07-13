package training.nadia.hospital.dao;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.entity.Treatment;

public interface TreatmentDao {

    Treatment getPatientTreatment(long patientId) throws DaoException;

    void updateNumberOfCompletedProcedure(long patientId, int numberOfProcedure) throws DaoException;
}
