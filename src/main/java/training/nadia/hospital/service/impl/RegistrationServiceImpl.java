package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.RegistrationDao;
import training.nadia.hospital.dao.UtilDao;
import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.impl.RegistrationDaoImpl;
import training.nadia.hospital.dao.impl.UtilDaoImpl;
import training.nadia.hospital.entity.User;
import training.nadia.hospital.service.RegistrationService;
import training.nadia.hospital.service.exception.ServiceException;

public class RegistrationServiceImpl implements RegistrationService {

    @Override
    public void register(User user) throws ServiceException {

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
    }

    private boolean userDoesNotExist(User user) throws DaoException {

        UtilDao utilDao = new UtilDaoImpl();
        return !utilDao.isUserExist(user);
    }
}
