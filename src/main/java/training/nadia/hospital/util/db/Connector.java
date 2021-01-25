package training.nadia.hospital.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static BasicConnectionPool instance;

    private Connector() {
    }

    public static Connection getConnection() {

        DataForConnectToDatabase data = new DataForConnectToDatabase();

        try {
            DriverManager.registerDriver(data.getDriver());

            if (instance == null) {
                instance = createBasicConnectionPool();
            }

        } catch (SQLException e){
            //логировать
        }


        return instance.getConnection();
    }

    public static void releaseConnection(Connection connection){
        instance.releaseConnection(connection);
    }

    private static BasicConnectionPool createBasicConnectionPool() throws SQLException {

        DataForConnectToDatabase data = new DataForConnectToDatabase();
        DriverManager.registerDriver(data.getDriver());

        return BasicConnectionPool.create(
                data.getUrl(),
                data.getUser(),
                data.getPassword());
    }
}
