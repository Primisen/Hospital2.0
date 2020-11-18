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

    private static final String SELECT_USER_ID_BY_USER_LOGIN = "select id from user where login=?";

    @Override
    public List<Doctor> getAllDoctors() throws DaoException {

        List<Doctor> doctors = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_DOCTORS)) {

            ps.setInt(1, Role.DOCTOR.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Doctor doctor = new Doctor();
                doctor.setName(rs.getString("name"));
                doctor.setSurname(rs.getString("surname"));
                doctor.setId(rs.getLong("id"));

                doctors.add(doctor);
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return doctors;
    }

    @Override
    public boolean isUserExist(User user) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_USER_ID_BY_USER_LOGIN_AND_PASSWORD)) {

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean isUserExist(String login) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_USER_ID_BY_USER_LOGIN)) {

            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return true;
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }

        return false;
    }
}
