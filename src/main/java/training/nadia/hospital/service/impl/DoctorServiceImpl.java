package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.exception.DaoException;
import training.nadia.hospital.dao.impl.DoctorDaoImpl;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.service.DoctorService;
import training.nadia.hospital.service.exception.ServiceException;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    private DoctorDaoImpl doctorDao = new DoctorDaoImpl();

    @Override
    public List<Doctor> getAllDoctors() throws ServiceException {//?

        List<Doctor> doctors;

        try {
            doctors = doctorDao.getAllDoctors();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

//        if (doctors != null) {
//            return doctors;
//        }
//        return new ArrayList<>();//?

        return doctors;
    }

}
