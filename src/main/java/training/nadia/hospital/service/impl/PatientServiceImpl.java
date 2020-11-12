package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.PatientDao;
import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.impl.PatientDaoImpl;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.service.PatientService;
import training.nadia.hospital.service.exception.ServiceException;

public class PatientServiceImpl implements PatientService {

    private PatientDao patientDao = new PatientDaoImpl();

    @Override
    public void goToTheDoctor(Patient patient, Doctor doctor) throws ServiceException {

        try {
            patientDao.setTreatingDoctor(patient, doctor);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void getTreatmentData(Patient patient) throws ServiceException {

        try {
            patientDao.getTreatmentData(patient);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
