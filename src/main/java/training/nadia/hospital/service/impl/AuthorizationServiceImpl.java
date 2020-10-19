package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.AuthorizationDao;
import training.nadia.hospital.dao.UtilDao;
import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.impl.*;
import training.nadia.hospital.entity.*;
import training.nadia.hospital.service.AuthorizationService;
import training.nadia.hospital.service.exception.ServiceException;

public class AuthorizationServiceImpl implements AuthorizationService {

    private AuthorizationDao authorizationDao = new AuthorizationDaoImpl();

    @Override
    public User authorize(String login, String password) throws ServiceException {


        try {

            if (isUserExist(login, password)) {

                User user = authorizationDao.getUserData(login);
                return user;

            } else {
                throw new ServiceException("Неправильно введен логин или пароль");
            }

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    private boolean isUserExist(String login, String password) throws DaoException {

        UtilDao utilDao = new UtilDaoImpl();

        boolean userNotExist = utilDao.getUser(login, password) == null;

        return !userNotExist;
    }
}
