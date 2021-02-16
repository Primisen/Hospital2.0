package training.nadia.hospital.dao.impl;

import training.nadia.hospital.dao.NurseDao;
import training.nadia.hospital.exception.DaoException;
import training.nadia.hospital.entity.Nurse;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.Treatment;
import training.nadia.hospital.util.db.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NurseDaoImpl implements NurseDao {

    private static final String SELECT_NURSE_DATA =
            "select user.id, user.name, user.surname, treatment_type_id, number_of_therapies, number_of_completed_therapies " +
                    "from treatment " +
                    "join user on treatment.patient_id = user.id ";

    private static final String UPDATE_NUMBER_OF_COMPLETED_THERAPIES =
            "update treatment set number_of_completed_therapies=? " +
                    "where patient_id=?;";

    @Override
    public void identifyNursePatients(Nurse nurse) throws DaoException {

        Connection connection = Connector.getConnection();
        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(SELECT_NURSE_DATA)) {

//            ps.setLong(1, nurse.getId());
            rs = ps.executeQuery();

            while (rs.next()) {

                Patient patient = new Patient();
                patient.setId(rs.getLong("id"));
                patient.setName(rs.getString("name"));
                patient.setSurname(rs.getString("surname"));

                Treatment treatment = new Treatment();
                treatment.setType(rs.getInt("treatment_type_id"));
                treatment.setNumberOfTherapies(rs.getInt("number_of_therapies"));
                treatment.setNumberOfCompletedTherapies(rs.getInt("number_of_completed_therapies"));

                patient.setTreatment(treatment);

                nurse.addPatient(patient);
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());

        } finally {

            if( rs != null){
                try {
                    rs.close();
                } catch (SQLException e){
                    throw new DaoException(e.getMessage());
                }
            }

            Connector.releaseConnection(connection);
        }
    }

    @Override
    public void updateNumberOfCompletedTherapies(Patient patient) throws DaoException {

        Connection connection = Connector.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_NUMBER_OF_COMPLETED_THERAPIES)) {

            ps.setInt(1, patient.getTreatment().getNumberOfCompletedTherapies());
            ps.setLong(2, patient.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());

        } finally {
            Connector.releaseConnection(connection);
        }
    }

}
