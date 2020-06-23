package training.nadia.hospital.dao;

import training.nadia.hospital.entity.MedicalStaff;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.User;

import java.sql.SQLException;

public interface UserDao {


    User findByLogin(String login) throws SQLException;

    Boolean save(User user);

    boolean userIsExist(String login);

    boolean passwordIsValidate(String login, String password);

    void getAllInfo(User user);

    MedicalStaff identifyTypeOfMedicalStaffByUserId(User user);

}
