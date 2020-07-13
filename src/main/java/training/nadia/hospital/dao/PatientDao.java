package training.nadia.hospital.dao;

import training.nadia.hospital.dao.exception.DaoException;

public interface PatientDao {

    void setReceivingDoctor(long patientId, long doctorId) throws DaoException;
}
