package training.nadia.hospital.dao;

import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.User;

import java.util.List;

public interface HospitalDao {

    boolean writeDataAfterAppointmentWithDoctor(Patient patient, Doctor doctor);

    boolean setTreatmentActive(Patient patient, User medicalStaff);

    boolean dischargeFromHospital(Patient patient);

    List<Patient> selectNursePatient();
}
