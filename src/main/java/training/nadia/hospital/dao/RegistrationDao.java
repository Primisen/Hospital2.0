package training.nadia.hospital.dao;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.entity.User;

public interface RegistrationDao {

    void addUser(User user) throws DaoException;
}
