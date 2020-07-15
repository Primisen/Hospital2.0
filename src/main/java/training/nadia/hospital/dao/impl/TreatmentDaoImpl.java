package training.nadia.hospital.dao.impl;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.TreatmentDao;
import training.nadia.hospital.entity.Treatment;
import training.nadia.hospital.entity.TreatmentType;
import training.nadia.hospital.util.connection_pool.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TreatmentDaoImpl implements TreatmentDao {

    private static final String SELECT_TREATMENT = "select treatment.active, treatment.treatment_is_done, treatment_type.id from patient " +
            "join treatment on patient.id=treatment.patient_id " +
            "join treatment_type on treatment.treatment_type_id=treatment_type.id " +
            "where patient.user_id=?";
    private static final String UPDATE_NUMBER_OF_COMPLETED_PROCEDURE = "update treatment set number_of_completed_therapies=? where patient_id=?";

    @Override
    public Treatment getPatientTreatment(long patientId) throws DaoException {

        Treatment treatment = new Treatment();

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_TREATMENT)) {

            ps.setLong(1, patientId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                treatment.setActive(rs.getBoolean("active"));
                treatment.setTreatmentIsDone(rs.getBoolean("treatment_is_done"));

                if (rs.getInt("id") == TreatmentType.PROCEDURE.getId()) {
                    treatment.setType(TreatmentType.PROCEDURE);

                } else if (rs.getInt("id") == TreatmentType.DRUG.getId()) {
                    treatment.setType(TreatmentType.DRUG);

                } else if (rs.getInt("id") == TreatmentType.OPERATION.getId()) {
                    treatment.setType(TreatmentType.OPERATION);
                }

                return treatment;
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }

        return null;
    }

    @Override
    public void updateNumberOfCompletedProcedure(long patientId, int numberOfProcedure) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_NUMBER_OF_COMPLETED_PROCEDURE)) {

            ps.setInt(1, numberOfProcedure);
            ps.setLong(2, patientId);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }


}
