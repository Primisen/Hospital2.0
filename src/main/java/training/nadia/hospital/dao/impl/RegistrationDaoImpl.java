package training.nadia.hospital.dao.impl;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.RegistrationDao;
import training.nadia.hospital.entity.MedicalStaff;
import training.nadia.hospital.entity.Role;
import training.nadia.hospital.entity.User;
import training.nadia.hospital.util.connection_pool.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationDaoImpl implements RegistrationDao {

    private static final String INSERT_USER = "insert into user (login, password, role_id, name, surname)" +
            " values (?, ?, ?, ?, ?)";

    private static final String INSERT_STAFF = "insert into staff (user_id, staff_type_id) values (?, ?)";
    private static final String INSERT_PATIENT = "insert into patient (user_id) value (?)";

    @Override
    public void addUser(User user) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_USER)) {

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getRoleId());
            ps.setString(4, user.getName());
            ps.setString(5, user.getSurname());

            ps.executeUpdate();

            if (user.getId() == Role.PATIENT.getId()) {
                addUserToPatientTable(user.getId());

            } else if (user.getId() == Role.MEDICAL_STAFF.getId()) {
                addUserToStaffTable(user);
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    private void addUserToPatientTable(long userId) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_PATIENT)) {

            ps.setLong(1, userId);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    private void addUserToStaffTable(User user) throws DaoException{

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_STAFF)) {

            ps.setLong(1, user.getId());

            MedicalStaff medicalStaff = (MedicalStaff) user; //??
            ps.setInt(2, medicalStaff.getStaffType().getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
