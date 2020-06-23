package training.nadia.hospital.service;

import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;

import java.util.List;

public interface DoctorService {

    List<Patient> getPatients(Doctor doctor);

    List<Doctor> getAllDoctors();
}
