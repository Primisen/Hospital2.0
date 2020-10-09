package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.impl.DoctorDaoImpl;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.Treatment;
import training.nadia.hospital.service.DoctorService;
import training.nadia.hospital.service.exception.ServiceException;

public class DoctorServiceImpl implements DoctorService {

    private DoctorDaoImpl doctorDao = new DoctorDaoImpl();

    @Override
    public void setDiagnosisAndTreatment(String diagnosis, Treatment treatment, Patient patient) throws ServiceException {

        try {
            doctorDao.setDiagnosis(diagnosis, patient.getId());
            doctorDao.setTreatment(treatment, patient.getId());
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void getPatients(Doctor doctor) throws ServiceException {

        try {
            doctorDao.identifyPatients(doctor);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
