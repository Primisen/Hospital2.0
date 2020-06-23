package training.nadia.hospital.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import training.nadia.hospital.entity.*;
import training.nadia.hospital.util.Connector;
import training.nadia.hospital.dao.UserDao;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    Logger logger = LogManager.getRootLogger();

    @Override
    public User findByLogin(String login) {

        User user = null;

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM user where login=?")) {

            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setRoleId(rs.getInt("role_id"));
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return user;
    }

    @Override
    public Boolean save(User user) {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement("insert into user (login, password, role_id, name, surname) values (?, ?, ?, ?, ?)")) {

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getRoleId());
            ps.setString(4, user.getName());
            ps.setString(5, user.getSurname());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public User findById(int id) {

        User user = new User();

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from user where id=?");
            ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setRoleId(rs.getInt("role_id"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setLogin(rs.getString("login"));
                user.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();//!
        }

        return user;
    }

    @Override
    public boolean userIsExist(String login) {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from user where login=?")) {

            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean passwordIsValidate(String login, String password) {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from user where login=? and password=?")) {

            ps.setString(1, login);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return false;
    }

    @Override
    public void getAllInfo(User user) {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from user where login=?")) {

            ps.setString(1, user.getLogin());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setRoleId(rs.getInt("role_id"));
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public MedicalStaff identifyTypeOfMedicalStaffByUserId(User user) {

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from staff where user_id=?")) {

            ps.setInt(1, user.getId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                if (rs.getInt("staff_type_id") == StaffType.DOCTOR.getId()) {

                    Doctor doctor = new Doctor();

                    doctor.setId(user.getId());
                    doctor.setLogin(user.getLogin());
                    doctor.setPassword(user.getPassword());
                    doctor.setName(user.getName());
                    doctor.setSurname(user.getSurname());

                    return doctor;

                } else if (rs.getInt("staff_type_id") == StaffType.NURSE.getId()) {

                    Nurse nurse = new Nurse();

                    nurse.setId(user.getId());
                    nurse.setLogin(user.getLogin());
                    nurse.setPassword(user.getPassword());
                    nurse.setName(user.getName());
                    nurse.setSurname(user.getSurname());

                    return nurse;
                }
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return null; //?
    }

}
