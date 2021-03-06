package training.nadia.hospital.dao.impl;

import training.nadia.hospital.exception.DaoException;
import training.nadia.hospital.entity.*;
import training.nadia.hospital.util.db.Connector;
import training.nadia.hospital.dao.DoctorDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorDaoImpl implements DoctorDao {

    private static final String INSERT_TREATMENT_DATA =
            "insert into treatment " +
                    "(patient_id, doctor_id, diagnosis, treatment_type_id, number_of_therapies, number_of_completed_therapies, active) " +
                    "values (?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_DOCTOR_PATIENTS =
            "select user.id, user.name, user.surname, user.login, diagnosis, number_of_therapies, " +
                    "number_of_completed_therapies, active, treatment_type_id from treatment " +
                    "join user on patient_id=user.id " +
                    "where doctor_id=? and active=?";

    private static final String SELECT_RECEIVING_PATIENTS =
            "select user.id, name, surname, login from user " +
                    "join reception on reception.patient_id = user.id " +
                    "where reception.doctor_id = ?";

    private static final String UPDATE_TREATMENT_ACTIVE = "update treatment set active=? where patient_id=?";

    private static final String DELETE_PATIENT_DATA_FROM_RECEPTION_TABLE = "delete from reception where patient_id=?";

    @Override
    public void setDiagnosisAndTreatment(Patient patient) throws DaoException {

        Connection connection = Connector.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(INSERT_TREATMENT_DATA);
            PreparedStatement deletePS = connection.prepareStatement(DELETE_PATIENT_DATA_FROM_RECEPTION_TABLE)) {

            ps.setLong(1, patient.getId());
            ps.setLong(2, patient.getTreatingDoctor().getId());
            ps.setString(3, patient.getDiagnosis());
            ps.setInt(4, patient.getTreatment().getType().getId());
            ps.setInt(5, patient.getTreatment().getNumberOfTherapies());
            ps.setInt(6, patient.getTreatment().getNumberOfCompletedTherapies());
            ps.setBoolean(7, patient.getTreatment().isActive());

            ps.executeUpdate();

            deletePS.setLong(1, patient.getId());
            deletePS.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());

        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public void getPatients(Doctor doctor) throws DaoException {

        Connection connection = Connector.getConnection();
        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(SELECT_DOCTOR_PATIENTS)) {

            ps.setLong(1, doctor.getId());
            ps.setBoolean(2, true);

            rs = ps.executeQuery();

            while (rs.next()) {

                Patient patient = new Patient();
                patient.setId(rs.getLong("id"));
                patient.setName(rs.getString("name"));
                patient.setSurname(rs.getString("surname"));
                patient.setLogin(rs.getString("login"));
                patient.setDiagnosis(rs.getString("diagnosis"));

                Treatment treatment = new Treatment();
                treatment.setType(rs.getInt("treatment_type_id"));
                treatment.setNumberOfTherapies(rs.getInt("number_of_therapies"));
                treatment.setNumberOfCompletedTherapies(rs.getInt("number_of_completed_therapies"));
                treatment.setActive(rs.getBoolean("active"));

                patient.setTreatment(treatment);

                doctor.addPatientsUndergoingTreatment(patient);
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DaoException(e.getMessage());
                }
            }

            Connector.releaseConnection(connection);
        }
    }

    @Override
    public void getReceivingPatients(Doctor doctor) throws DaoException {

        Connection connection = Connector.getConnection();
        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(SELECT_RECEIVING_PATIENTS)) {

            ps.setLong(1, doctor.getId());

            rs = ps.executeQuery();

            while (rs.next()) {

                Patient patient = new Patient();
                patient.setId(rs.getLong("id"));
                patient.setName(rs.getString("name"));
                patient.setSurname(rs.getString("surname"));
                patient.setLogin(rs.getString("login"));

                doctor.addPatientWhoNeedToBeCheckup(patient);
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DaoException(e.getMessage());
                }
            }

            Connector.releaseConnection(connection);
        }
    }

    @Override
    public void dischargePatient(Patient patient) throws DaoException {

        Connection connection = Connector.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_TREATMENT_ACTIVE)) {

            ps.setBoolean(1, patient.getTreatment().isActive());
            ps.setLong(2, patient.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());

        } finally {
            Connector.releaseConnection(connection);
        }
    }
}
