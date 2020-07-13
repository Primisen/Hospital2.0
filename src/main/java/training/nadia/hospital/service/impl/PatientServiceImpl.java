package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.PatientDao;
import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.impl.PatientDaoImpl;
import training.nadia.hospital.service.PatientService;

public class PatientServiceImpl implements PatientService {

    @Override
    public void goToTheDoctor(long patientId, long doctorId) {

        PatientDaoImpl patientDao = new PatientDaoImpl();
        try {
            patientDao.setReceivingDoctor(patientId, doctorId);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
