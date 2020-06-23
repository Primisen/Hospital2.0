package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.impl.DoctorDaoImpl;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.service.DoctorService;

import java.util.ArrayList;
import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    private DoctorDaoImpl doctorDao = new DoctorDaoImpl();

    @Override
    public List<Patient> getPatients(Doctor doctor) {
        return doctorDao.getPatientsWithAppointments(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {//?

        List<Doctor> doctors = doctorDao.getAllDoctors();

        if (doctors != null) {
            return doctors;
        }
        return doctors = new ArrayList<>();//?
    }
}
