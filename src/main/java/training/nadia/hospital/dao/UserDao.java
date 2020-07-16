package training.nadia.hospital.dao;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Nurse;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.User;

public interface UserDao {

    User getUser(String login, String password) throws DaoException;

    void setAllData(Patient patient) throws DaoException;
    void setAllData(Doctor doctor) throws DaoException;
    void setAllData(Nurse nurse) throws DaoException;
}
