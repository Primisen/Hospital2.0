package training.nadia.hospital.util;

import training.nadia.hospital.entity.Patient;

import java.util.Set;

public class Utility {

    private Utility() {
    }

    public static Patient findPatientById(Set<Patient> patients, long id) {

        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }

        return null;
    }
}
