package training.nadia.hospital.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool implements ConnectionPool {

    private static BasicConnectionPool instance;

    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static final int INITIAL_POLL_SIZE = 10;

    private BasicConnectionPool(String url, String user, String password, List<Connection> connectionPool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }

    public static BasicConnectionPool create(String url, String user, String password) throws SQLException {

        if (instance == null) {

            List<Connection> pool = new ArrayList<>(INITIAL_POLL_SIZE);
            for (int i = 0; i < INITIAL_POLL_SIZE; i++) {
                pool.add(createConnection(url, user, password));
            }

            instance = new BasicConnectionPool(url, user, password, pool);
        }

        return instance;
    }

    @Override
    public Connection getConnection() {

        if (connectionPool.size() > 0) {
            Connection connection = connectionPool.remove(connectionPool.size() - 1);
            usedConnections.add(connection);
            return connection;
        }

        return usedConnections.get(usedConnections.size() - 1);
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    private static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
