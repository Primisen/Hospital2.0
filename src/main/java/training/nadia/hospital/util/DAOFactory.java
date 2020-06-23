package training.nadia.hospital.util;

public class DAOFactory {

    private static final DAOFactory INSTANCE = new DAOFactory();

//    private final UserDAO user = new UserDataBase();

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return INSTANCE;
    }

//    public UserDAO getUser(){
//        return user;
//    }
}
