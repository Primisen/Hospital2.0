package training.nadia.hospital.dao.impl;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.MedicalStaffDao;
import training.nadia.hospital.util.connection_pool.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicalStaffDaoImpl implements MedicalStaffDao {

    private static final String SELECT_STAFF_TYPE_ID = "select staff_type_id from staff join user on user.id=staff.user_id where user.login=?";

    @Override
    public int getStaffTypeId(String login) throws DaoException {

        try {
            try (Connection connection = Connector.getConnection();
                 PreparedStatement ps = connection.prepareStatement(SELECT_STAFF_TYPE_ID)) {

                ps.setString(1, login);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getInt("staff_type_id");
                }
            }
        } catch (SQLException e) {
            new DaoException(e.getMessage());
        }

        return 0;
    }
}
