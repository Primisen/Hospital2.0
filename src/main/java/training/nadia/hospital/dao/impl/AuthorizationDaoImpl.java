package training.nadia.hospital.dao.impl;

import training.nadia.hospital.dao.AuthorizationDao;
import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.util.connection_pool.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorizationDaoImpl implements AuthorizationDao {

    private static final String SELECT_ROLE_ID_BY_USER_LOGIN = "select role_id from user where login=?";

    @Override
    public int getRoleId(String login) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ROLE_ID_BY_USER_LOGIN)) {

            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();

            int roleId = 0;

            if (rs.next()) {
                roleId = rs.getInt("role_id");

            } else {
                throw new DaoException("User with this login does not exist.");
            }

            return roleId;

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
