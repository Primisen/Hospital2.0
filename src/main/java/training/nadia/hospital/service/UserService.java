package training.nadia.hospital.service;

import training.nadia.hospital.entity.MedicalStaff;
import training.nadia.hospital.entity.User;

public interface UserService {

    void login(User user);

    String registration(User user);

    MedicalStaff getStaff(User user);
}
