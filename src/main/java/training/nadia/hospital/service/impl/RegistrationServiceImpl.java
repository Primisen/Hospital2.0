package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.RegistrationDao;
import training.nadia.hospital.dao.UtilDao;
import training.nadia.hospital.exception.DaoException;
import training.nadia.hospital.dao.impl.RegistrationDaoImpl;
import training.nadia.hospital.dao.impl.UtilDaoImpl;
import training.nadia.hospital.entity.User;
import training.nadia.hospital.service.RegistrationService;
import training.nadia.hospital.exception.ServiceException;
import training.nadia.hospital.service.user_factory.UserFactory;

public class RegistrationServiceImpl implements RegistrationService {

    @Override
    public User register(User user) throws ServiceException {

        try {

            if (userDoesNotExist(user)) {
                RegistrationDao registrationDao = new RegistrationDaoImpl();
                registrationDao.add(user);

            } else {
                throw new ServiceException("User with this login already exists.");
            }

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        return UserFactory.createUser(user.getRole().getId());
    }

    private boolean userDoesNotExist(User user) throws DaoException {

        UtilDao utilDao = new UtilDaoImpl();
        return !utilDao.isUserExist(user.getLogin());
    }
}
