package training.nadia.hospital.service.user_factory;

import training.nadia.hospital.entity.*;

public class UserFactory {

    private UserFactory() {
    }

    public static User createUser(int roleId) {

        if (Role.PATIENT.getId() == roleId) {
            return new Patient();

        } else if (Role.DOCTOR.getId() == roleId) {
            return new Doctor();

        } else if (Role.NURSE.getId() == roleId) {
            return new Nurse();
        }

        return null;
    }
}
