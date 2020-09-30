package training.nadia.hospital.dao;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Nurse;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.User;

public interface AuthorizationDao {

    int getRoleId(String login) throws DaoException;//kick

    User getData(String login) throws DaoException;

    void getData(Patient patient) throws DaoException;

    void getData(Doctor doctor) throws DaoException;

    void getData(Nurse nurse) throws DaoException;
}
