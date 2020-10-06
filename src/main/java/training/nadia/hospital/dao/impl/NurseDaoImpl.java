package training.nadia.hospital.dao.impl;

import training.nadia.hospital.dao.NurseDao;
import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.entity.Nurse;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.Treatment;
import training.nadia.hospital.util.connection_pool.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NurseDaoImpl implements NurseDao {

    private static final String SELECT_NURSE_DATA =
            "select patient.id, user.name, user.surname, treatment_type_id, number_of_therapies, number_of_completed_therapies, " +
                    "if (active = 0, 1, 0) " +
                    "from treatment " +
                    "join patient on patient.id = treatment.patient_id " +
                    "join user on patient.user_id = user.id " +
                    "join staff on staff.user_id = treatment.nurse_id " +
                    "where staff.user_id = ?";

    private static final String UPDATE_NUMBER_OF_COMPLETED_THERAPIES =
            "update treatment set number_of_completed_therapies=?" +
                    "where patient_id=?;";

    @Override
    public void identifyNursePatients(Nurse nurse) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_NURSE_DATA)) {

            ps.setLong(1, nurse.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Patient patient = new Patient(rs.getString("name"), rs.getString("surname"));
                patient.setId(rs.getLong("id"));

                Treatment treatment = new Treatment();
                treatment.setNumberOfTherapies(rs.getInt("number_of_therapies"));
                treatment.setType(rs.getInt("treatment_type_id"));
                treatment.setNumberOfCompletedTherapies(rs.getInt("number_of_completed_therapies"));

                patient.setTreatment(treatment);

                nurse.addPatientTherapies(patient, treatment.getNumberOfTherapies());
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void updateNumberOfCompletedTherapies(Patient patient, int numberOfTherapies) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_NUMBER_OF_COMPLETED_THERAPIES)) {

            ps.setLong(1, patient.getId());

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

}
