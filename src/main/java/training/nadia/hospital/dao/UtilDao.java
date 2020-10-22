package training.nadia.hospital.dao;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.User;

import java.util.List;

public interface UtilDao {

    List<Doctor> getAllDoctors() throws DaoException;

    boolean isUserExist(User user) throws DaoException;
}
