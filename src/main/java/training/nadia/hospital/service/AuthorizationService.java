package training.nadia.hospital.service;

import training.nadia.hospital.entity.User;
import training.nadia.hospital.service.exception.ServiceException;

public interface AuthorizationService {

    void authorize(User user) throws ServiceException;
}
