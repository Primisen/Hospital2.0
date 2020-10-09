package training.nadia.hospital.service;

import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.service.exception.ServiceException;

import java.util.List;

public interface UtilService {

    List<Doctor> getAllDoctors() throws ServiceException;
}
