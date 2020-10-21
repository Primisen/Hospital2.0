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
            doctorDao.setDiagnosis(diagnosis, patient);
            doctorDao.setTreatment(treatment, patient);

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void getPatients(Doctor doctor) throws ServiceException {

        try {
            doctorDao.getPatients(doctor);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void getReceivingPatients(Doctor doctor) throws ServiceException {

        try {
            doctorDao.getReceivingPatients(doctor);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void dischargePatient(Patient patient) throws ServiceException {

        if (isNumberOfTherapiesEqualsNumberOfCompletedTherapies(patient)) {

            patient.getTreatment().setActive(false);

            try {
                doctorDao.dischargePatient(patient);
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage());
            }

        } else {
            throw new ServiceException("The patient has nao been treated!");
        }
    }

    private boolean isNumberOfTherapiesEqualsNumberOfCompletedTherapies(Patient patient) {

        return
                patient.getTreatment().getNumberOfTherapies() ==
                        patient.getTreatment().getNumberOfCompletedTherapies();
    }
}
