package training.nadia.hospital.dao.impl;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.entity.*;
import training.nadia.hospital.util.connection_pool.Connector;
import training.nadia.hospital.dao.DoctorDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//общее
public class DoctorDaoImpl implements DoctorDao {

    private static final String SELECT_DOCTORS = "select staff.id, user.name, user.surname from staff join user on user.id=staff.user_id where staff.staff_type_id=?";

    @Override
    public List<Doctor> getAllDoctors() throws DaoException{

        List<Doctor> doctors = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_DOCTORS)) {

            ps.setInt(1, StaffType.DOCTOR.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) { //вынести в метод, передать resultSet, exception

                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setName(rs.getString("name"));
                doctor.setSurname(rs.getString("surname"));

                doctors.add(doctor);
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }

        return doctors;
    }
}
