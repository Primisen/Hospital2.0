package training.nadia.hospital.dao.impl;

import training.nadia.hospital.dao.AuthorizationDao;
import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.entity.*;
import training.nadia.hospital.util.connection_pool.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorizationDaoImpl implements AuthorizationDao {

    private static final String SELECT_USER_DATA = "select id, role_id, password, name, surname from user where login=?";

    private static final String SELECT_PATIENT_DATA =
            "select diagnosis, treatment_type_id, active, treatment_is_done, user.name, user.surname from treatment " +
                    "join patient on treatment.patient_id=patient.id " +
                    "join staff on treatment.doctor_id = staff.id join user on staff.user_id=user.id " +
                    "where patient.user_id=?";

    private static final String SELECT_DOCTOR_DATA =
            "select diagnosis, treatment_type_id, number_of_therapies, number_of_completed_therapies, active, treatment_is_done, user.name, user.surname from treatment " +
                    "join staff on treatment.doctor_id = staff.id " +
                    "join patient on treatment.patient_id = patient.id " +
                    "join user on user.id = patient.user_id " +
                    "where staff.user_id=?";

    private static final String SELECT_RECEIVING_PATIENTS =
            "select name, surname from user " +
                    "join patient on patient.user_id=user.id " +
                    "join staff on staff.id=patient.receiving_doctor_id " +
                    "where staff.user_id=? and patient.id not in ( " +
                    " select patient_id from treatment where staff.user_id=?)";

    private static final String SELECT_NURSE_DATA =
            "select user.name, user.surname, treatment_type_id, number_of_therapies, number_of_completed_therapies, " +
                    "if (active = 0, 1, 0) " +
                    "from treatment " +
                    "join patient on patient.id = treatment.patient_id " +
                    "join user on patient.user_id = user.id " +
                    "join staff on staff.user_id = treatment.nurse_id " +
                    "where staff.user_id = ?";


    @Override
    public User getData(String login) throws DaoException {

        User user = new User();
        user.setLogin(login);

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_USER_DATA)) {

            ps.setString(1, login);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {

                user.setId(resultSet.getLong("id"));
                user.setRole(resultSet.getInt("role_id"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }

        return user;
    }

    @Override
    public void getData(Patient patient) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_PATIENT_DATA)) {

            ps.setLong(1, patient.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                patient.setTreatingDoctor(
                        new Doctor(
                                rs.getString("name"),
                                rs.getString("surname")));

                patient.setDiagnosis(rs.getString("diagnosis"));

                patient.setTreatment(
                        new Treatment(
                                rs.getInt("treatment_type_id"),
                                rs.getBoolean("active"),
                                rs.getBoolean("treatment_is_done")));
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void getData(Doctor doctor) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_DOCTOR_DATA);
             PreparedStatement receivingPatientPS = connection.prepareStatement(SELECT_RECEIVING_PATIENTS)) {

            ps.setLong(1, doctor.getId());

            receivingPatientPS.setLong(1, doctor.getId());
            receivingPatientPS.setLong(2, doctor.getId());

            ResultSet rs = ps.executeQuery();

            List<Patient> patientToCure = new ArrayList<>();
            List<Patient> patientToReceive = new ArrayList<>();

            while (rs.next()) {

                Patient patient = new Patient(rs.getString("name"), rs.getString("surname"));
                patient.setDiagnosis(rs.getString("diagnosis"));

                Treatment treatment = new Treatment();
                treatment.setType(rs.getInt("treatment_type_id"));
                treatment.setActive(rs.getBoolean("active"));
                treatment.setTreatmentIsDone(rs.getBoolean("treatment_is_done"));
                treatment.setNumberOfTherapies(rs.getInt("number_of_therapies"));
                treatment.setNumberOfCompletedTherapies(rs.getInt("number_of_completed_therapies"));

                patient.setTreatment(treatment);
                patientToCure.add(patient);
            }

            rs = receivingPatientPS.executeQuery();

            while (rs.next()) {

                patientToReceive.add(new Patient(rs.getString("name"), rs.getString("surname")));
            }

            doctor.setPatientsToCure(patientToCure);
            doctor.setPatientsToReceive(patientToReceive);

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void getData(Nurse nurse) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_NURSE_DATA)) {

            ps.setLong(1, nurse.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Patient patient = new Patient(rs.getString("name"), rs.getString("surname"));

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
}
