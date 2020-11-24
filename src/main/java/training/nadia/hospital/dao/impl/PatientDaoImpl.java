package training.nadia.hospital.dao.impl;

import training.nadia.hospital.dao.PatientDao;
import training.nadia.hospital.exception.DaoException;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.Treatment;
import training.nadia.hospital.util.db.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDaoImpl implements PatientDao {

    private static final String INSERT_TREATING_DOCTOR = "insert into reception (`patient_id`, `doctor_id`) values (?, ?);";

    private static final String SELECT_TREATMENT_DATA =
            "select diagnosis, treatment_type_id, active, user.name, user.surname from treatment " +
                    "join user on treatment.doctor_id=user.id " +
                    "where patient_id=?";

    private static final String SELECT_RECEPTION_DOCTOR =
            "select user.name, user.surname from reception " +
                    "join user on doctor_id = user.id " +
                    "where patient_id = ?";

    @Override
    public void setTreatingDoctor(Patient patient, Doctor doctor) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_TREATING_DOCTOR)) {

            ps.setLong(1, patient.getId());
            ps.setLong(2, doctor.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void getTreatmentData(Patient patient) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_TREATMENT_DATA)) {

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
                                rs.getBoolean("active")));
            }

            getReceptionDoctor(patient);

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    private void getReceptionDoctor(Patient patient) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_RECEPTION_DOCTOR)) {

            ps.setLong(1, patient.getId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Doctor doctor = new Doctor();
                doctor.setName(rs.getString("name"));
                doctor.setSurname(rs.getString("surname"));
                patient.setReceptionDoctor(doctor);
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
