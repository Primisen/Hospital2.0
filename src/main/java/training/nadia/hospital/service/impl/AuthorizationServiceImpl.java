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
    public User getUser(String login, String password) throws ServiceException {//kick

        User user = new User();//возможно можно убрать
        user.setLogin(login);
        user.setPassword(password);

        try {

            if (userDao.getUser(user) != null) {

                int roleId = authorizationDao.getRoleId(login);

                if (roleId == Role.PATIENT.getId()) {

                    Patient patient = new Patient();
                    patient.setLogin(login);
                    patient.setPassword(password);

                    userDao.setAllData(patient);

                    return patient;

                } else if (roleId == Role.MEDICAL_STAFF.getId()) {

                    MedicalStaffDaoImpl medicalStaffDao = new MedicalStaffDaoImpl(); //нужно ли мне здесь отдельное дао?
                    int medicalStaffRoleId = medicalStaffDao.getStaffTypeId(login);

                    if (medicalStaffRoleId == StaffType.DOCTOR.getId()) {

                        Doctor doctor = new Doctor();
                        doctor.setLogin(login);
                        doctor.setPassword(password);

                        userDao.setAllData(doctor);

                        return doctor;

                    } else if (medicalStaffRoleId == StaffType.NURSE.getId()) {

                        Nurse nurse = new Nurse();
                        nurse.setLogin(login);
                        nurse.setPassword(password);

                        userDao.setAllData(nurse);

                        return nurse;
                    }
                }

            } else {

                throw new ServiceException("This user does not exists.");
                //не верно введен логин или пароль

                //обоврачивать в сервис эксептион
            }
        } catch (DaoException e) { //?
            throw new ServiceException(e.getMessage());
        }

        return null;
    }

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
