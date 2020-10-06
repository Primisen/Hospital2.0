package training.nadia.hospital.service;

import training.nadia.hospital.entity.Nurse;
import training.nadia.hospital.service.exception.ServiceException;

public interface NurseService {

    void performTheProcedure(Nurse nurse, long patientId) throws ServiceException;

    void identifyNursePatients(Nurse nurse) throws ServiceException;
}
