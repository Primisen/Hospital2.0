package training.nadia.hospital.service;

import training.nadia.hospital.service.exception.ServiceException;

public interface PatientService {

    void goToTheDoctor(long patientId, long doctorId) throws ServiceException;
}
