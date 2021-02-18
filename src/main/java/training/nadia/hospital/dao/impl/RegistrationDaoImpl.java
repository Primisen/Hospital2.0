package training.nadia.hospital.dao.impl;

import training.nadia.hospital.exception.DaoException;
import training.nadia.hospital.dao.RegistrationDao;
import training.nadia.hospital.entity.User;
import training.nadia.hospital.util.db.Connector;

import java.sql.*;

public class RegistrationDaoImpl implements RegistrationDao {

    private static final String INSERT_INTO_USER_TABLE = "insert into user (login, password, role_id, name, surname)" +
            " values (?, ?, ?, ?, ?)";

    @Override
    public void add(User user) throws DaoException {

        Connection connection = Connector.getConnection();

        saveGeneralData(user, connection);

        Connector.releaseConnection(connection);
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

        rs.close();
    }
}
