package training.nadia.hospital.dao.impl;

import org.apache.log4j.Logger;
import training.nadia.hospital.util.Connector;
import training.nadia.hospital.dao.PatientDao;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao {

    private Logger logger = Logger.getRootLogger();

    @Override
    public void writeReceivingDoctorId(Doctor doctor) {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement("insert into patient (receiving_doctor_id) value (?)")) {

            ps.setInt(1, doctor.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Patient findPatientByUserId(int id) {

        Patient patient = new Patient();

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from user where id=?")
             ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            patient.setId(id);

            patient.setName(rs.getString("name"));
            patient.setSurname(rs.getString("surname"));
            patient.setLogin(rs.getString("login"));
            patient.setPassword(rs.getString("password"));
            patient.setRoleId(rs.getInt("role_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patient;
    }

    @Override
    public List<Patient> getAllPatients() {

        List<Patient> patients = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * from patient");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Patient patient = new Patient();
                patient.setId(rs.getInt("user_id"));

                //+записать доктора!

                PreparedStatement psUser = connection.prepareStatement("SELECT * FROM user where id=?");

                psUser.setInt(1,  + patient.getId());

                ResultSet rsUser = psUser.executeQuery();

                if (rsUser.next()) {

                    patient.setName(rsUser.getString("name"));
                    patient.setSurname(rsUser.getString("surname"));
                    patient.setLogin(rsUser.getString("login"));
                    patient.setPassword(rsUser.getString("password"));
                    patient.setRoleId(rsUser.getInt("role_id"));
                }

                patients.add(patient);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return patients;
    }
}
