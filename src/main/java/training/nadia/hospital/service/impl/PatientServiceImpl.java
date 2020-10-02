package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.impl.PatientDaoImpl;
import training.nadia.hospital.service.PatientService;
import training.nadia.hospital.service.exception.ServiceException;

public class PatientServiceImpl implements PatientService {

    @Override
    public void goToTheDoctor(long patientId, long doctorId) throws ServiceException {

        PatientDaoImpl patientDao = new PatientDaoImpl();

        try {
            patientDao.setReceivingDoctor(patientId, doctorId);

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
