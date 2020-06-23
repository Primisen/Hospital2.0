package training.nadia.hospital.dao;

import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;

import java.util.List;

public interface DoctorDao {

    List<Patient> getPatientsWithAppointments(Doctor doctor);

    List<Doctor> getAllDoctors();
}
