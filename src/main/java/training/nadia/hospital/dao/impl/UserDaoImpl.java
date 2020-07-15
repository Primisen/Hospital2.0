package training.nadia.hospital.dao.impl;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.UserDao;
import training.nadia.hospital.entity.*;
import training.nadia.hospital.util.connection_pool.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao { //БОЛЬШОЙ РЕФАКТОРИН ПО ДОБЫВАНИЮ ID, NAME И SURNAME

    private static final String SELECT_USER_ID_BY_USER_LOGIN_AND_PASSWORD = "select id from user where login=? and password=?";

    private static final String SELECT_ALL_INFO_ABOUT_PATIENT = "select patient.id, user.name, user.surname, " +
            "patient.receiving_doctor_id, treatment.doctor_id, \n" +
            "treatment.diagnosis, treatment.treatment_type_id, treatment.active,\n" +
            "treatment.treatment_is_done from user \n" +
            "join patient on patient.user_id=user.id \n" +
            "join treatment on patient.id=treatment.patient_id\n" +
            "where login=?";

    private static final String SELECT_TREATING_DOCTOR = "select user.name, user.surname from user\n" +
            "join staff on user.id=staff.user_id\n" +
            "join treatment on staff.id=treatment.doctor_id\n" +
            "where treatment.patient_id=?";

    private static final String SELECT_RECEIVING_DOCTOR = "select user.name, user.surname from user\n" +
            "join staff on user.id=staff.user_id\n" +
            "join patient on staff.id=patient.receiving_doctor_id\n" +
            "where patient.id=?";

    private static final String SELECT_PATIENT_BASE_INFO = "select patient.id, user.name, user.surname from patient\n" +
            "join user on user.id=patient.user_id\n" +
            "where user.login=?";

    private static final String SELECT_PATIENTS_WITH_APPOINTMENTS = "select * from patient where receiving_doctor_id=?";

    private static final String SELECT_NAME_AND_SURNAME_OF_RECEIVING_PATIENTS =
            "select user.name, user.surname from patient " +
                    "join user on user.id = patient.user_id " +
                    "where patient.receiving_doctor_id = ? " +
                    "and patient.id not in (" +
                    "select patient_id from treatment where treatment_is_done = false" +
                    ");";

    private static final String SELECT_ID_NAME_SURNAME_OF_DOCTOR =
            "select staff.id, user.name, user.surname from staff join user on user.id = staff.user_id where login=?";

    private static final String SELECT_DATA_ABOUT_PATIENTS_TO_CURE =
            "select user.name, user.surname, treatment.diagnosis, treatment.treatment_type_id, treatment.number_of_therapies, treatment.number_of_completed_therapies, treatment.active, treatment.treatment_is_done " +
                    "from treatment " +
                    "join patient on patient.id = treatment.patient_id " +
                    "join user on user.id=patient.user_id " +
                    "where treatment.doctor_id=?";

    private static final String SELECT_NAME_AND_SURNAME_OF_USER_BY_LOGIN = "select user.name, user.surname from user where login=?";
    private static final String SELECT_ID_OF_PATIENT_BY_LOGIN = "select patient.id from patient join user on user.id=patient.user_id where user.login=?";
    private static final String SELECT_ID_OF_MEDICAL_STAFF_BY_LOGIN = "select staff.id from staff join user on user.id=staff.user_id where user.login=?";

    private static final String SELECT_THERAPIES_THAT_THE_NURSE_SHOULD_PERFORM =
            "select user.name, user.surname, treatment.number_of_therapies, treatment.number_of_completed_therapies " +
                    "from staff " +
                    "join treatment on staff.id=treatment.nurse_id " +
                    "join patient on patient.id=treatment.patient_id " +
                    "join user on user.id=patient.user_id " +
                    "where staff.id=?";

    @Override
    public User getUser(String login, String password) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_USER_ID_BY_USER_LOGIN_AND_PASSWORD)) {

            ps.setString(1, login);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User();
                user.setLogin(login);
                user.setPassword(password);
                user.setId(rs.getLong("id"));

                return user;
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }

        return null;
    }

    @Override
    public void setAllData(Patient patient) throws DaoException {
        try (Connection connection = Connector.getConnection();
             PreparedStatement baseDatePS = connection.prepareStatement(SELECT_PATIENT_BASE_INFO);
             PreparedStatement receivingDoctorPS = connection.prepareStatement("select receiving_doctor_id from patient where id=?");
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_INFO_ABOUT_PATIENT)) {

            baseDatePS.setString(1, patient.getLogin());
            ResultSet rs = baseDatePS.executeQuery();

            if (rs.next()) {
                patient.setId(rs.getLong("id"));
                patient.setName(rs.getString("name"));
                patient.setSurname(rs.getString("surname"));
            }

            receivingDoctorPS.setLong(1, patient.getId());

            rs = receivingDoctorPS.executeQuery();

            if (rs.next()) {
                if (getReceivingDoctor(patient) != null) {
                    patient.setReceivingDoctor(getReceivingDoctor(patient));
                }
            } else {
                return;
            }

            ps.setString(1, patient.getLogin());

            rs = ps.executeQuery();

            if (rs.next()) {

//                UserDaoImpl userDao = new UserDaoImpl();
//                userDao.getBaseInfo(patient, rs);

//                patient.setReceivingDoctor(getReceivingDoctor(patient));
                patient.setTreatingDoctor(getTreatingDoctor(patient));

                TreatmentDaoImpl treatmentDao = new TreatmentDaoImpl();
                patient.setTreatment(treatmentDao.getPatientTreatment(patient.getId()));

                patient.setDiagnosis(rs.getString("diagnosis"));
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void setAllData(Doctor doctor) throws DaoException {

        setUserData(doctor);

        doctor.setPatientsToReceive(getReceivingPatient(doctor));
        doctor.setPatientsToCure(getPatientsToCure(doctor));
    }

    @Override
    public void setAllData(Nurse nurse) throws DaoException {

        setUserData(nurse);

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_THERAPIES_THAT_THE_NURSE_SHOULD_PERFORM)) {

            ps.setLong(1, nurse.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Patient patient = new Patient(); //такой код точно уже был
                patient.setName(rs.getString("name"));
                patient.setSurname(rs.getString("surname"));

                int numberOfTherapies = rs.getInt("number_of_therapies") - rs.getInt("number_of_completed_therapies");

                nurse.setPatientTherapies(patient, numberOfTherapies);
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }

    }

    private Doctor getTreatingDoctor(Patient patient) {
        return getDoctor(patient, SELECT_TREATING_DOCTOR);
    }

    private Doctor getReceivingDoctor(Patient patient) {
        return getDoctor(patient, SELECT_RECEIVING_DOCTOR);
    }

    private Doctor getDoctor(Patient patient, String request) {

        Doctor doctor = new Doctor();

        try (Connection connection = Connector.getConnection(); //connection в connection
             PreparedStatement ps = connection.prepareStatement(request)) {

            ps.setLong(1, patient.getId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                doctor.setName(rs.getString("name"));
                doctor.setSurname(rs.getString("surname"));

                return doctor;
            }

        } catch (SQLException e) {
            e.printStackTrace(); //!
        }

        return null;
    }


    private List<Patient> getPatientsToCure(Doctor doctor) {

        List<Patient> patients = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_DATA_ABOUT_PATIENTS_TO_CURE)) {

            ps.setLong(1, doctor.getId());

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Patient patient = new Patient();
                patient.setName(resultSet.getString("name"));
                patient.setSurname(resultSet.getString("surname"));

                patient.setDiagnosis(resultSet.getString("diagnosis"));

                int treatmentTypeId = resultSet.getInt("treatment_type_id");

                if (treatmentTypeId == TreatmentType.PROCEDURE.getId()) {
                    patient.getTreatment().setType(TreatmentType.PROCEDURE);

                } else if (treatmentTypeId == TreatmentType.DRUG.getId()) {
                    patient.getTreatment().setType(TreatmentType.DRUG);

                } else if (treatmentTypeId == TreatmentType.OPERATION.getId()) {
                    patient.getTreatment().setType(TreatmentType.OPERATION);
                }

                patient.getTreatment().setActive(resultSet.getBoolean("active"));
                patient.getTreatment().setTreatmentIsDone(resultSet.getBoolean("treatment_is_done"));

                patient.getTreatment().setNumberOfTherapies(resultSet.getInt("number_of_therapies"));
                patient.getTreatment().setNumberOfCompletedTherapies(resultSet.getInt("number_of_completed_therapies"));

                patients.add(patient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    private List<Patient> getReceivingPatient(Doctor doctor) {

        List<Patient> patients = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_NAME_AND_SURNAME_OF_RECEIVING_PATIENTS)) {

            ps.setLong(1, doctor.getId());

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Patient patient = new Patient();
                patient.setName(resultSet.getString("name"));
                patient.setSurname(resultSet.getString("surname"));

                patients.add(patient);
            }

            return patients;

        } catch (SQLException e) {
            e.printStackTrace(); ///????
        }

        return patients;
    }

    private void setUserData(Patient patient) {
        setUserData(patient, SELECT_ID_OF_PATIENT_BY_LOGIN);
    }

    private void setUserData(MedicalStaff medicalStaff) {
        setUserData((User) medicalStaff, SELECT_ID_OF_MEDICAL_STAFF_BY_LOGIN);
    }

    private void setUserData(User user, String query) {

        setNameAndSurname(user);

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, user.getLogin());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setId(rs.getLong("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();//!
        }
    }

    private void setNameAndSurname(User user) {
        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_NAME_AND_SURNAME_OF_USER_BY_LOGIN)) {

            ps.setString(1, user.getLogin());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
            }

        } catch (SQLException e) {
            e.printStackTrace();//!
        }
    }
}
