package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.impl.TreatmentDaoImpl;
import training.nadia.hospital.entity.Nurse;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.service.NurseService;

import java.util.HashSet;
import java.util.Set;

public class NurseServiceImpl implements NurseService {

    @Override
    public void performTheProcedure(Nurse nurse, long patientId) {

        Set<Patient> patients = new HashSet<>();
        patients = nurse.getPatientTherapies().keySet();

        Patient patient = findPatientById(patients, patientId);

        TreatmentDaoImpl treatmentDao = new TreatmentDaoImpl();
        try {
            treatmentDao.updateNumberOfCompletedProcedure(patientId, nurse.getPatientTherapies().get(patient));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    private Patient findPatientById(Set<Patient> patients, long patientId) {

        for (Patient patient : patients) {
            if (patient.getId() == patientId) {
                return patient;
            }
        }

        return null;
    }
}
