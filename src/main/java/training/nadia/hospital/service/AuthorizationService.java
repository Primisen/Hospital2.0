package training.nadia.hospital.service;

import training.nadia.hospital.entity.MedicalStaff;
import training.nadia.hospital.entity.User;
import training.nadia.hospital.service.exception.ServiceException;

public interface AuthorizationService {

    User getUser(String login, String password) throws ServiceException;//kick

    User authorize(String login, String password) throws ServiceException;
}
