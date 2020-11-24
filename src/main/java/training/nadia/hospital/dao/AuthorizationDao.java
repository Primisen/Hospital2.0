package training.nadia.hospital.dao;

import training.nadia.hospital.exception.DaoException;
import training.nadia.hospital.entity.User;

public interface AuthorizationDao {

    void initializeUserData(User user) throws DaoException;
}
