package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.UtilDao;
import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.impl.UtilDaoImpl;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.service.UtilService;
import training.nadia.hospital.service.exception.ServiceException;

import java.util.List;

public class UtilServiceImpl implements UtilService {

    @Override
    public List<Doctor> getAllDoctors() throws ServiceException {

        UtilDao utilDao = new UtilDaoImpl();

        try {
            return utilDao.getAllDoctors();

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }


}
