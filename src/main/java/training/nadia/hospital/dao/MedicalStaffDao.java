package training.nadia.hospital.dao;

import training.nadia.hospital.dao.exception.DaoException;

public interface MedicalStaffDao {

    int getStaffTypeId(String login) throws DaoException;
}
