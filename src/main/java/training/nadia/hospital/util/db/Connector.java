package training.nadia.hospital.util.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private Connector() {
    }

    public static Connection getConnection() throws SQLException {

        DataForConnectToDatabase data = new DataForConnectToDatabase();

        Driver driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);

        Connection connection = BasicConnectionPool.create(
                data.getUrl(),
                data.getUser(),
                data.getPassword())

                .getConnection();

        return connection;
    }
}
