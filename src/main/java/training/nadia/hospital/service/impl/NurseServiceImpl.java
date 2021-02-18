package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.NurseDao;
import training.nadia.hospital.exception.DaoException;
import training.nadia.hospital.dao.impl.NurseDaoImpl;
import training.nadia.hospital.entity.Nurse;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.service.NurseService;
import training.nadia.hospital.exception.ServiceException;

import java.util.Set;

public class NurseServiceImpl implements NurseService {

    private NurseDao nurseDao = new NurseDaoImpl();

    @Override
    public void performTheTherapy(Nurse nurse, long patientId) throws ServiceException {

        Patient patient = findPatientById(nurse.getPatient(), patientId);

        nurse.getPatient().remove(patient);//?

        if (patient.getTreatment().getNumberOfCompletedTherapies() < patient.getTreatment().getNumberOfTherapies()) {

            patient.getTreatment().setNumberOfCompletedTherapies(
                    patient.getTreatment().getNumberOfCompletedTherapies() + 1);
        }

        nurse.addPatient(patient);

        try {
            nurseDao.updateNumberOfCompletedTherapies(patient);

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void identifyNursePatients(Nurse nurse) throws ServiceException {

        try {
            nurseDao.identifyNursePatients(nurse);

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    private Patient findPatientById(Set<Patient> patients, long patientId) {//думаю есть способ поиска получше

        for (Patient patient : patients) {
            if (patient.getId() == patientId) {
                return patient;
            }
        }

        return null;
    }
}
