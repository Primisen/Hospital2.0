package training.nadia.hospital.dao.impl;

import org.apache.log4j.Logger;
import training.nadia.hospital.util.Connector;
import training.nadia.hospital.dao.HospitalDao;
import training.nadia.hospital.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HospitalDaoImpl implements HospitalDao {

    private Logger logger = Logger.getRootLogger();

    private static final String SET_TREATMENT_TO_PATIENT = "insert into treatment (patient_id, diagnosis, treatment_type_id) values (?, ?, ?)";
    private static final String SET_TREATMENT_IS_ACTIVE = "insert into treatment (staff_id, active) values (?, ?) where patient_id=?";
    private static final String  DISCHARGE_FROM_HOSPITAL = "insert into treatment (treatment_is_done) value (?) where patient_id=?";

    private static final String SELECT_NURSE_PATIENTS = "select * from treatment inner join patient on treatment.patient_id=patient.id " +
            "where treatment_type_id!=? and activity=? and treatment_is_done=?";



    @Override
    public boolean writeDataAfterAppointmentWithDoctor(Patient patient, Doctor doctor) {

        writeDoctorIdToPatient(patient, doctor);

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SET_TREATMENT_TO_PATIENT)) {

            ps.setInt(1, findPatientIdByUserId(patient));
            ps.setString(2, patient.getDiagnosis().toString());
            ps.setInt(3, getTreatmentTypeId(patient.getTreatment().getType()));

            return true;

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean setTreatmentActive(Patient patient, User medicalStaff) {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SET_TREATMENT_IS_ACTIVE)) {

            ps.setInt(1, findStaffIdByUserId(medicalStaff));
            ps.setBoolean(2, patient.getTreatment().isActive());
            ps.setInt(3, findPatientIdByUserId(patient));

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean dischargeFromHospital(Patient patient) {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(DISCHARGE_FROM_HOSPITAL)) {

            ps.setBoolean(1, true);
            ps.setInt(2, patient.getId());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return false;
    }

    @Override
    public List<Patient> selectNursePatient() {

        List<Patient> patients = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_NURSE_PATIENTS)) {

            ps.setInt(1, TreatmentType.OPERATION.getId());
            ps.setBoolean(2, false);
            ps.setBoolean(3, false);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Patient patient = new Patient();

                ps.setInt(1, rs.getInt("user_id"));


            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return patients;
    }

    private int findPatientIdByUserId(Patient patient) {
        return findIdByUserId(patient, "patient");
    }

    private int findStaffIdByUserId(User staff) {
        return findIdByUserId(staff, "staff");
    }

    private int findIdByUserId(User user, String tableName) {

        int id = 0;

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from " + tableName + " where user_id=?")
        ) {

            ps.setInt(1, user.getId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return id;
    }

    private void writeDoctorIdToPatient(Patient patient, Doctor doctor) {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement("insert into patient (staff_id) value (?) where user_id=?")) {

            ps.setInt(1, doctor.getId());
            ps.setInt(1, patient.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    private int findPatientIdByLogin(User user) {

        String login = user.getLogin();
        int id = 0;

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM treatment where login=?")) {

            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                id = rs.getInt("id");
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return id;
    }

    private int getTreatmentTypeId(TreatmentType treatment) {

        int procedureId = 1;
        int drugId = 2;
        int operationId = 3;

        if (treatment == TreatmentType.PROCEDURE) {
            return procedureId;
        } else if (treatment == TreatmentType.DRUG) {
            return drugId;
        } else if (treatment == TreatmentType.OPERATION) {
            return operationId;
        }

        return 0;
    }
}

