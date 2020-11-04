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
    public void authorize(User user) throws ServiceException {

        try {

            if (isUserExist(user)) {
                authorizationDao.initializeUserData(user);

            } else {
                throw new ServiceException("Incorrect login or password!");
            }

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    private boolean isUserExist(User user) throws DaoException {

        UtilDao utilDao = new UtilDaoImpl();

        return utilDao.isUserExist(user);
    }
}
