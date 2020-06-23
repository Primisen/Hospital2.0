package training.nadia.hospital.service.impl;

import training.nadia.hospital.dao.impl.UserDaoImpl;
import training.nadia.hospital.entity.MedicalStaff;
import training.nadia.hospital.entity.User;
import training.nadia.hospital.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public String registration(User user) {

        User findUser = userDao.findByLogin(user.getLogin());

        if (findUser == null) {

            userDao.save(user);
            return "registration is done";
        }

        return "registration is not done";
    }

    //    @Override
    public User findById(int id) {
        //чекать

        return userDao.findById(id);
    }

    @Override
    public void login(User user) {

        if (userDao.userIsExist(user.getLogin()) && userDao.passwordIsValidate(user.getLogin(), user.getPassword())) {

            userDao.getAllInfo(user);


        } else {

            //не верно введен логин или пароль
        }
    }

    @Override
    public MedicalStaff getStaff(User user) {
        return userDao.identifyTypeOfMedicalStaffByUserId(user);
    }
}
