package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.impl.*;
import training.nadia.hospital.entity.*;
import training.nadia.hospital.service.AuthorizationService;
import training.nadia.hospital.service.exception.ServiceException;

public class AuthorizationServiceImpl implements AuthorizationService {

    private AuthorizationDaoImpl authorizationDao = new AuthorizationDaoImpl();
    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public User authorize(String login, String password) throws ServiceException {

        try {

            User user = authorizationDao.getData(login);

            if (!user.getPassword().equals(password)) {
                throw new ServiceException("Неправильно введен логин или пароль!");//!

            } else if (user.getRole().equals(Role.PATIENT)) {
                Patient patient = new Patient();
                copy(user, patient);
                authorizationDao.getData(patient);
                return patient;

            } else if (user.getRole().equals(Role.DOCTOR)) {
                Doctor doctor = new Doctor();
                copy(user, doctor);
                authorizationDao.getData(doctor);
                return doctor;

            } else if (user.getRole().equals(Role.NURSE)) {
                Nurse nurse = new Nurse();
                copy(user, nurse);
                authorizationDao.getData(nurse);
                return nurse;
            }

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        return null;
    }

    private void copy(User source, User destination) {

        destination.setRole(source.getRole());
        destination.setId(source.getId());
        destination.setName(source.getName());
        destination.setSurname(source.getSurname());
        destination.setLogin(source.getLogin());
        destination.setPassword(source.getPassword());
    }
}
