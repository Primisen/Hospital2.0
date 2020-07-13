package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.impl.RegistrationDaoImpl;
import training.nadia.hospital.dao.impl.UserDaoImpl;
import training.nadia.hospital.entity.User;
import training.nadia.hospital.service.RegistrationService;
import training.nadia.hospital.service.exception.ServiceException;


public class RegistrationServiceImpl implements RegistrationService {

    @Override
    public void registration(User user) throws ServiceException {

        UserDaoImpl userDao = new UserDaoImpl();

        try {

            if (userDao.getUser(user.getLogin(), user.getPassword()) == null) {

                RegistrationDaoImpl registrationDao = new RegistrationDaoImpl();
                registrationDao.addUser(user);

            } else {
                throw new ServiceException("This login is exist");
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
