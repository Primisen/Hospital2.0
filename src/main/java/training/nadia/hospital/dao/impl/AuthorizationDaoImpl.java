package training.nadia.hospital.dao.impl;

import training.nadia.hospital.dao.AuthorizationDao;
import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.entity.*;
import training.nadia.hospital.util.db.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorizationDaoImpl implements AuthorizationDao {

    private static final String SELECT_USER_DATA = "select id, role_id, name, surname from user where login=?";

    @Override
    public void initializeUserData(User user) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_USER_DATA)) {

            ps.setString(1, user.getLogin());

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                user.setId(resultSet.getLong("id"));
                user.setRole(resultSet.getInt("role_id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
