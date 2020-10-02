package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.impl.RegistrationDaoImpl;
import training.nadia.hospital.dao.impl.UserDaoImpl;
import training.nadia.hospital.entity.User;
import training.nadia.hospital.service.RegistrationService;
import training.nadia.hospital.service.exception.ServiceException;

public class RegistrationServiceImpl implements RegistrationService {

    @Override
    public void register(User user) throws ServiceException {

        try {
            if (userDoesNotExist(user)) {

                RegistrationDaoImpl registrationDao = new RegistrationDaoImpl();
                registrationDao.add(user);

            } else {
                throw new ServiceException("This user is exist.");//text?
            }

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    private boolean userDoesNotExist(User user) throws DaoException {

        UserDaoImpl userDao = new UserDaoImpl();
        return userDao.getUser(user) == null;
    }
}
