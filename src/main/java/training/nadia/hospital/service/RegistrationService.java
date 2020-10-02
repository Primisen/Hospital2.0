package training.nadia.hospital.service;

import training.nadia.hospital.entity.User;
import training.nadia.hospital.service.exception.ServiceException;

public interface RegistrationService {

    void register(User user) throws ServiceException;
}
