package training.nadia.hospital.util;

import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Nurse;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.User;

public class CopyData {

    private CopyData() {
    }

    public static void copy(User base, Doctor derived) {

        derived.setLogin(base.getLogin());
        derived.setPassword(base.getPassword());
        derived.setId(base.getId());
        derived.setName(base.getName());
        derived.setSurname(base.getSurname());
    }

    public static void copy(User base, Patient derived) {

        derived.setLogin(base.getLogin());
        derived.setPassword(base.getPassword());
        derived.setId(base.getId());
        derived.setName(base.getName());
        derived.setSurname(base.getSurname());
    }

    public static void copy(User base, Nurse derived) {

        derived.setLogin(base.getLogin());
        derived.setPassword(base.getPassword());
        derived.setId(base.getId());
        derived.setName(base.getName());
        derived.setSurname(base.getSurname());
    }
}
