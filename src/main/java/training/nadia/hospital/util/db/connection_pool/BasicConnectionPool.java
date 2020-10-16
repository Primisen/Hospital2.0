package training.nadia.hospital.util.connection_pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class BasicConnectionPool implements ConnectionPool { //синглтон, сделать статическим, ограничить видимость(только по пакету)

    private String url;
    private String user;
    private String password;
    private static List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POLL_SIZE = 10;

    public BasicConnectionPool(String url, String user, String password, List<Connection> pool) {
        this.url = url;
        this.user = user;
        this.password = password;
//        connectionPool = new ArrayList<>();
    }

    public static BasicConnectionPool create(String url, String user, String password) throws SQLException {

        connectionPool = new ArrayList<>(INITIAL_POLL_SIZE);
        for (int i = 0; i < INITIAL_POLL_SIZE; i++) {
            connectionPool.add(createConnection(url, user, password));
        }

        return new BasicConnectionPool(url, user, password, connectionPool);
    }

    @Override
    public Connection getConnection() {//проверка на колво, а то выпаду в -1
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);

    }

    private static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    @Override
    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
