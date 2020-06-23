package training.nadia.hospital.dao;

import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;

import java.util.List;

public interface PatientDao {

    void writeReceivingDoctorId(Doctor doctor);

    Patient findPatientByUserId(int id);

    List<Patient> getAllPatients();
}
