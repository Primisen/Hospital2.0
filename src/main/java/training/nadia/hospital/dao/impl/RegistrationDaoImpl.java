package training.nadia.hospital.dao.impl;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.RegistrationDao;
import training.nadia.hospital.entity.MedicalStaff;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.Role;
import training.nadia.hospital.entity.User;
import training.nadia.hospital.util.connection_pool.Connector;

import java.sql.*;

public class RegistrationDaoImpl implements RegistrationDao {

    private static final String INSERT_INTO_USER_TABLE = "insert into user (login, password, role_id, name, surname)" +
            " values (?, ?, ?, ?, ?)";

    private static final String INSERT_INTO_STAFF_TABLE = "insert into staff (user_id, staff_type_id) values (?, ?)";
    private static final String INSERT_INTO_PATIENT_TABLE = "insert into patient (user_id) value (?)";

    @Override
    public void add(Patient user) throws DaoException {

        addDataToUserTable(user);

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_INTO_PATIENT_TABLE, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, user.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void add(MedicalStaff medicalStaff) throws DaoException {

        User user = (User) medicalStaff;
        addDataToUserTable(user);

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_INTO_STAFF_TABLE, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, user.getId());
            ps.setInt(2, medicalStaff.getStaffType().getId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ((User) medicalStaff).setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    private void addDataToUserTable(User user) throws DaoException {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_INTO_USER_TABLE, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getRoleId());
            ps.setString(4, user.getName());
            ps.setString(5, user.getSurname());

            ps.executeUpdate();

            setUserId(user, ps);

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    private void setUserId(User user, PreparedStatement ps) throws SQLException {

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            user.setId(rs.getLong(1));
        }
    }

}
