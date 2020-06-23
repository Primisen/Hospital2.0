package training.nadia.hospital.dao.impl;

import org.apache.log4j.Logger;
import training.nadia.hospital.entity.StaffType;
import training.nadia.hospital.util.Connector;
import training.nadia.hospital.dao.DoctorDao;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {

    private Logger logger = Logger.getRootLogger();

    private static final String SELECT_PATIENTS_WITH_APPOINTMENTS = "select * from patient where receiving_doctor_id=?";
    private static final String SELECT_DOCTORS = "select * from staff join user on user.id=staff.user_id where staff_type_id=?";

    @Override
    public List<Patient> getPatientsWithAppointments(Doctor doctor) {

        List<Patient> patients = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_PATIENTS_WITH_APPOINTMENTS)) {

            ps.setInt(1, doctor.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                PatientDaoImpl patientDao = new PatientDaoImpl();

                Patient patient = patientDao.findPatientByUserId(rs.getInt("user_id"));

                patients.add(patient);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return patients;
    }

    @Override
    public List<Doctor> getAllDoctors() {

        List<Doctor> doctors = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_DOCTORS)) {

            ps.setInt(1, StaffType.DOCTOR.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("user_id"));
                doctor.setName(rs.getString("name"));
                doctor.setSurname(rs.getString("surname"));
                doctor.setLogin(rs.getString("login"));
                doctor.setPassword(rs.getString("password"));
                doctor.setRoleId(rs.getInt("role_id"));

                doctors.add(doctor);
            }

        } catch (SQLException e) {

            logger.error(e.getMessage());
        }

        return doctors;
    }
}
