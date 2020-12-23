package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.DoctorDao;
import training.nadia.hospital.exception.DaoException;
import training.nadia.hospital.dao.impl.DoctorDaoImpl;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.Treatment;
import training.nadia.hospital.service.DoctorService;
import training.nadia.hospital.exception.ServiceException;

public class DoctorServiceImpl implements DoctorService {

    private DoctorDao doctorDao = new DoctorDaoImpl();

    @Override
    public void setDiagnosisAndTreatment(String diagnosis, Treatment treatment, Patient patient, Doctor doctor) throws ServiceException {

        try {
            if (diagnosis != null && treatment != null) {

                treatment.setActive(true);
                patient.setTreatment(treatment);
                patient.setDiagnosis(diagnosis);
                patient.setTreatingDoctor(doctor);

                doctorDao.setDiagnosisAndTreatment(patient);//вынести в приватный метод

                doctor.getPatientsToReceive().remove(patient);
                doctor.addPatientToCure(patient);
            }

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
            throw new ServiceException("The patient has not been treated!");
        }
    }

    private boolean isNumberOfTherapiesEqualsNumberOfCompletedTherapies(Patient patient) {

        return
                patient.getTreatment().getNumberOfTherapies() ==
                        patient.getTreatment().getNumberOfCompletedTherapies();
    }
}
