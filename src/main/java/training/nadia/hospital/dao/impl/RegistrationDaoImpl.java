package training.nadia.hospital.dao.impl;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.RegistrationDao;
import training.nadia.hospital.entity.Role;
import training.nadia.hospital.entity.User;
import training.nadia.hospital.util.connection_pool.Connector;

import java.sql.*;

public class RegistrationDaoImpl implements RegistrationDao {

    private static final String INSERT_INTO_USER_TABLE = "insert into user (login, password, role_id, name, surname)" +
            " values (?, ?, ?, ?, ?)";

    private static final String INSERT_INTO_STAFF_TABLE = "insert into staff (user_id) value (?)";
    private static final String INSERT_INTO_PATIENT_TABLE = "insert into patient (user_id) value (?)";

    @Override
    public void add(User user) throws DaoException {

        try (Connection connection = Connector.getConnection()) {

            saveGeneralData(user, connection);
            saveDataDependingOnUserRole(user, connection);

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    private void saveGeneralData(User user, Connection connection) throws DaoException {

        try (PreparedStatement ps = connection.prepareStatement(INSERT_INTO_USER_TABLE, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getRole().getId());
            ps.setString(4, user.getName());
            ps.setString(5, user.getSurname());

            ps.executeUpdate();

            setUserId(user, ps);

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    private void setUserId(User user, PreparedStatement preparedStatement) throws SQLException {

        ResultSet rs = preparedStatement.getGeneratedKeys();

        while (rs.next()) {
            user.setId(rs.getLong(1));
        }
    }

    private void saveDataDependingOnUserRole(User user, Connection connection) throws DaoException {

        if (user.getRole() == Role.PATIENT) {
            insertUserDataIntoRoleTable(user, connection, INSERT_INTO_PATIENT_TABLE);

        } else if (user.getRole() == Role.DOCTOR || user.getRole() == Role.NURSE) {
            insertUserDataIntoRoleTable(user, connection, INSERT_INTO_STAFF_TABLE);
        }
    }

    private void insertUserDataIntoRoleTable(User user, Connection connection, String insert) throws DaoException {

        try (PreparedStatement ps = connection.prepareStatement(insert)) {

            ps.setLong(1, user.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
