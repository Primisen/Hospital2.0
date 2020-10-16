package training.nadia.hospital.util.db;

import training.nadia.hospital.util.db.connection_pool.BasicConnectionPool;
import training.nadia.hospital.util.db.data_for_connect_to_db.DataForConnectToDatabase;
import training.nadia.hospital.util.db.data_for_connect_to_db.DataForConnectToDatabaseFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private Connector() { }

    public static Connection getConnection() throws SQLException {

        DataForConnectToDatabase data = DataForConnectToDatabaseFactory.getInstance();

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
