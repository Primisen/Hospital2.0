package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.AuthorizationDao;
import training.nadia.hospital.dao.UtilDao;
import training.nadia.hospital.exception.DaoException;
import training.nadia.hospital.dao.impl.*;
import training.nadia.hospital.entity.*;
import training.nadia.hospital.service.AuthorizationService;
import training.nadia.hospital.exception.ServiceException;
import training.nadia.hospital.service.user_factory.UserFactory;

public class AuthorizationServiceImpl implements AuthorizationService {

    private final AuthorizationDao authorizationDao = new AuthorizationDaoImpl();

    @Override
    public User authorize(String login, String password) throws ServiceException {

        try {

            if (isUserExist(login, password)) {

                User user = UserFactory.createUser(authorizationDao.defineRoleId(login));

                user.setLogin(login);
                user.setPassword(password);
                authorizationDao.initializeUserData(user);

                return user;

            } else {
                throw new ServiceException("Incorrect login or password!");
            }

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    private boolean isUserExist(String login, String password) throws DaoException {

        UtilDao utilDao = new UtilDaoImpl();
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);

        return utilDao.isUserExist(user);
    }
}
