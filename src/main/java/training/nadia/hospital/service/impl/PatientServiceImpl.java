package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.impl.PatientDaoImpl;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.service.PatientService;

import java.util.ArrayList;
import java.util.List;

public class PatientServiceImpl implements PatientService {

    private PatientDaoImpl patientDao = new PatientDaoImpl();

    @Override
    public List<Patient> getAllPatients() {//?

        List<Patient> patients = patientDao.getAllPatients();

        if (patients != null) {
            return patients;
        }

        return patients = new ArrayList<>();//?
    }
}
