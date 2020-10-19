package training.nadia.hospital.dao.impl;

import training.nadia.hospital.dao.UtilDao;
import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Role;
import training.nadia.hospital.entity.User;
import training.nadia.hospital.util.db.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilDaoImpl implements UtilDao {

    private static final String SELECT_ALL_DOCTORS = "select id, name, surname from user where role_id=?";

    private static final String SELECT_USER_ID_BY_USER_LOGIN_AND_PASSWORD = "select id from user where login=? and password=?";

    @Override
    public List<Doctor> getAllDoctors() throws DaoException {

        List<Doctor> doctors = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_DOCTORS)) {

            ps.setInt(1, Role.DOCTOR.getId());
            ps.executeUpdate();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Doctor doctor = new Doctor();
                doctor.setName(rs.getString("name"));
                doctor.setSurname(rs.getString("surname"));
                doctor.setId(rs.getLong("id"));
                doctors.add(new Doctor(rs.getString("name"), rs.getString("surname")));
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return doctors;
    }

    @Override
    public User getUser(String login, String password) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_USER_ID_BY_USER_LOGIN_AND_PASSWORD)) {

            ps.setString(1, login);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            User user = new User();

            if (rs.next()) {//зачем это здесь происходит)

                user.setId(rs.getLong("id"));

                return user;
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }

        return null;
    }
}
