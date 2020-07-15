package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.impl.RegistrationDaoImpl;
import training.nadia.hospital.dao.impl.UserDaoImpl;
import training.nadia.hospital.entity.MedicalStaff;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.Role;
import training.nadia.hospital.entity.User;
import training.nadia.hospital.service.RegistrationService;
import training.nadia.hospital.service.exception.ServiceException;

public class RegistrationServiceImpl implements RegistrationService {

    @Override
    public void register(User user) throws ServiceException {

        try {
            if (userDoesNotExist(user)) {

               registerUserByRole(user);

            } else {
                throw new ServiceException("This login is exist.");
            }

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    private void registerUserByRole(User user) throws DaoException {

        RegistrationDaoImpl registrationDao = new RegistrationDaoImpl();

        if (user.getRoleId() == Role.PATIENT.getId()) {
            Patient patient = (Patient) user;
            registrationDao.add(patient);

        } else if (user.getRoleId() == Role.MEDICAL_STAFF.getId()) {
            MedicalStaff medicalStaff = (MedicalStaff) user;
            registrationDao.add(medicalStaff);
        }
    }

    private boolean userDoesNotExist(User user) throws DaoException {

        UserDaoImpl userDao = new UserDaoImpl();
        return userDao.getUser(user.getLogin(), user.getPassword()) == null;
    }
}
