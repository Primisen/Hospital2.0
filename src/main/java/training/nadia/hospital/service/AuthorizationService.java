package training.nadia.hospital.service;

import training.nadia.hospital.entity.User;
import training.nadia.hospital.exception.ServiceException;

public interface AuthorizationService {

    User authorize(String login, String password) throws ServiceException;
}
