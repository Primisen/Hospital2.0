package training.nadia.hospital.dao.impl;

import training.nadia.hospital.dao.PatientDao;
import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.util.connection_pool.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientDaoImpl implements PatientDao {

    @Override
    public void setReceivingDoctor(long patientId, long doctorId) throws DaoException {

        try(Connection connection = Connector.getConnection();
            PreparedStatement ps = connection.prepareStatement("update patient set receiving_doctor_id=? where id=?")) {

            ps.setLong(1, doctorId);
            ps.setLong(2, patientId);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
