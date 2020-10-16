package training.nadia.hospital.util.connection_pool;

import org.apache.log4j.Logger;
import training.nadia.hospital.util.connection_pool.BasicConnectionPool;
import training.nadia.hospital.util.data_for_connect_to_db.DataForConnectToDatabase;
import training.nadia.hospital.util.data_for_connect_to_db.DataForConnectToDatabaseFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static Logger logger = Logger.getRootLogger();

    private Connector() {
    }

    public static Connection getConnection() {

        DataForConnectToDatabase data = DataForConnectToDatabaseFactory.getInstance();
        Connection connection = null;

        try {

            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

//            Class.forName("com.mysql.cj.jdbc.Driver"); //нужен ли он ??

            connection = BasicConnectionPool.create(
                    data.getUrl(),
                    data.getUser(),
                    data.getPassword())

                    .getConnection();

        } catch (SQLException e) {
            logger.error(e.getMessage());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        }

        return connection;
    }
}
