package training.nadia.hospital.util.connection_pool;

import java.sql.Connection;

public interface ConnectionPool {

    Connection getConnection();
    boolean releaseConnection(Connection connection);
    String getUrl();
    String getUser();
    String getPassword();
}