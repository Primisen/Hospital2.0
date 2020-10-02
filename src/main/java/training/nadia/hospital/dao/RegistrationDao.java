package training.nadia.hospital.dao;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.entity.User;

public interface RegistrationDao {

    void add(User user) throws DaoException;
 }
