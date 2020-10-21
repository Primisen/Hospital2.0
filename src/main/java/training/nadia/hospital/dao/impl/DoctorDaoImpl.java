package training.nadia.hospital.dao.impl;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.entity.*;
import training.nadia.hospital.util.db.Connector;
import training.nadia.hospital.dao.DoctorDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorDaoImpl implements DoctorDao {

    private static final String UPDATE_DIAGNOSIS = "update treatment set diagnosis=? where patient_id=?";

    private static final String UPDATE_TREATMENT =
            "update treatment set number_of_therapies=?, treatment_type_id=" +
                    "(select id from treatment_type where name = ?) " +
                    "where patient_id=?";

    private static final String SELECT_DOCTOR_PATIENTS =
            "select user.name, user.surname, diagnosis, number_of_therapies, " +
                    "number_of_completed_therapies, active, treatment_type_id from treatment " +
                    "join user on patient_id=user.id " +
                    "where doctor_id=?";

    private static final String SELECT_RECEIVING_PATIENTS =
            "select name, surname from user " +
                    "join patient on patient.user_id = user.id " +
                    "where patient.receiving_doctor_id = ?";

    private static final String UPDATE_TREATMENT_ACTIVE = "update treatment set active=? where patient_id=?";

    @Override
    public void setDiagnosis(String diagnosis, Patient patient) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_DIAGNOSIS)) {

            ps.setString(1, diagnosis);
            ps.setLong(2, patient.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void setTreatment(Treatment treatment, Patient patient) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_TREATMENT)) {

            ps.setInt(1, treatment.getNumberOfTherapies());
            ps.setString(2, treatment.getType().getRussianName());
            ps.setLong(3, patient.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void getPatients(Doctor doctor) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_DOCTOR_PATIENTS)) {

            ps.setLong(1, doctor.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Patient patient = new Patient();
                patient.setName(rs.getString("name"));
                patient.setSurname(rs.getString("surname"));
                patient.setDiagnosis(rs.getString("diagnosis"));

                Treatment treatment = new Treatment();
                treatment.setType(rs.getInt("treatment_type_id"));
                treatment.setNumberOfTherapies(rs.getInt("number_of_therapies"));
                treatment.setNumberOfCompletedTherapies(rs.getInt("number_of_completed_therapies"));
                treatment.setActive(rs.getBoolean("active"));

                patient.setTreatment(treatment);

                doctor.addPatientToCure(patient);
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void getReceivingPatients(Doctor doctor) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_RECEIVING_PATIENTS)) {

            ps.setLong(1, doctor.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Patient patient = new Patient();
                patient.setName("name");
                patient.setSurname("surname");

                doctor.addPatientToReceive(patient);
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void dischargePatient(Patient patient) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_TREATMENT_ACTIVE)) {

            ps.setBoolean(1, patient.getTreatment().isActive());
            ps.setLong(2, patient.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
