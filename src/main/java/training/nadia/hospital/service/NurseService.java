package training.nadia.hospital.service;

import training.nadia.hospital.entity.Nurse;
import training.nadia.hospital.entity.Patient;

public interface NurseService {

    void performTheProcedure(Nurse nurse, long patientId);
}
