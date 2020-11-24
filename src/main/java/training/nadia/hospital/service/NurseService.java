package training.nadia.hospital.service;

import training.nadia.hospital.entity.Nurse;
import training.nadia.hospital.exception.ServiceException;

public interface NurseService {

    void performTheTherapy(Nurse nurse, long patientId) throws ServiceException;

    void identifyNursePatients(Nurse nurse) throws ServiceException;
}
