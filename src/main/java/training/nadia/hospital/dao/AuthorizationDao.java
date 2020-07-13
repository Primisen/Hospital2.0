package training.nadia.hospital.dao;


import training.nadia.hospital.dao.exception.DaoException;

public interface AuthorizationDao {

    int getRoleId(String login) throws DaoException;
}
