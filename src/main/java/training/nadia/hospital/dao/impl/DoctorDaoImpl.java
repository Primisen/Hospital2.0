package training.nadia.hospital.dao.impl;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.entity.*;
import training.nadia.hospital.util.connection_pool.Connector;
import training.nadia.hospital.dao.DoctorDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorDaoImpl implements DoctorDao {

    private static final String SELECT_DOCTORS =
            "select staff.id, user.name, user.surname from staff join user on user.id=staff.user_id where staff.staff_type_id=?";//kick

    private static final String UPDATE_DIAGNOSIS = "update treatment set diagnosis=? where patient_id=?";

    private static final String UPDATE_TREATMENT =
            "update treatment set number_of_therapies=?, treatment_type_id=" +
                    "(select id from treatment_type where name = ?) " +
                    "where patient_id=?";

    private static final String SET_DOCTOR_PATIENTS =
            "select user.name, user.surname, diagnosis, number_of_therapies, number_of_completed_therapies, active, treatment_type_id from treatment " +
                    "join user on patient_id=user.id " +
                    "where doctor_id=?";

    @Override
    public void setDiagnosis(String diagnosis, long patientId) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_DIAGNOSIS)) {

            ps.setString(1, diagnosis);
            ps.setLong(2, patientId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void setTreatment(Treatment treatment, long patientId) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_TREATMENT)) {

            ps.setInt(1, treatment.getNumberOfTherapies());
            ps.setString(2, treatment.getType().getRussianName());
            ps.setLong(3, patientId);


        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void identifyPatients(Doctor doctor) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SET_DOCTOR_PATIENTS)) {

            ps.setLong(1, doctor.getId());
            ps.executeUpdate();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Patient patient = new Patient(rs.getString("name"), rs.getString("surname"));
                patient.setDiagnosis(rs.getString("diagnosis"));

                Treatment treatment = new Treatment();
                treatment.setType(rs.getInt("treatment_type_id"));
                treatment.setNumberOfTherapies(rs.getInt("number_of_therapies"));
                treatment.setNumberOfCompletedTherapies(rs.getInt("number_of_completed_therapies"));
                treatment.setActive(rs.getBoolean("active"));

                patient.setTreatment(treatment);

                doctor.addPatientToCureList(patient);
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
