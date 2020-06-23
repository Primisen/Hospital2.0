package training.nadia.hospital.dao;

import training.nadia.hospital.dao.impl.DoctorDaoImpl;
import training.nadia.hospital.dao.impl.HospitalDaoImpl;
import training.nadia.hospital.dao.impl.PatientDaoImpl;
import training.nadia.hospital.dao.impl.UserDaoImpl;

public final class FactoryDao {

    private static final FactoryDao INSTANCE = new FactoryDao();

    private final DoctorDao doctorDao = new DoctorDaoImpl();
    private final HospitalDao hospitalDao = new HospitalDaoImpl();
    private final PatientDao patientDao = new PatientDaoImpl();
    private final UserDao userDao = new UserDaoImpl();

    public static FactoryDao getInstance(){
        return INSTANCE;
    }

    public DoctorDao getDoctorDao() {
        return doctorDao;
    }

    public HospitalDao getHospitalDao() {
        return hospitalDao;
    }

    public PatientDao getPatientDao() {
        return patientDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
