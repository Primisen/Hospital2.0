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
    public User getUser(String login, String password) throws ServiceException {

        try {

            if (userDao.getUser(login, password) != null) {

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
}
