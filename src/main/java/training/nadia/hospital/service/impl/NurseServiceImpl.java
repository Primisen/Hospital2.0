package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.NurseDao;
import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.impl.NurseDaoImpl;
import training.nadia.hospital.entity.Nurse;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.service.NurseService;
import training.nadia.hospital.service.exception.ServiceException;

import java.util.Set;

public class NurseServiceImpl implements NurseService {

    private NurseDao nurseDao = new NurseDaoImpl();

    @Override
    public void performTheProcedure(Nurse nurse, long patientId) throws ServiceException {

        Set<Patient> patients = nurse.getPatientTherapies().keySet();
        Patient patient = findPatientById(patients, patientId);

        int numberOfCompletedTherapies = nurse.getPatientTherapies().get(patient);

        try {
            nurseDao.updateNumberOfCompletedTherapies(patient, numberOfCompletedTherapies);

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

    private Patient findPatientById(Set<Patient> patients, long patientId) {

        for (Patient patient : patients) { //поищи решение получше
            if (patient.getId() == patientId) {
                return patient;
            }
        }

        return null;
    }
}
