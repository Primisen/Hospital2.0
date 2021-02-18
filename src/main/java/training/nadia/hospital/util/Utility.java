package training.nadia.hospital.util;

import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.Role;
import training.nadia.hospital.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    public static void redirect(User user, HttpServletResponse response) throws IOException {

        if (user.getRole() == Role.PATIENT) {
            response.sendRedirect("/patient");

        } else if (user.getRole() == Role.DOCTOR) {
            response.sendRedirect("/doctor");

        } else if (user.getRole() == Role.NURSE) {
            response.sendRedirect("/nurse");
        }
    }
}
