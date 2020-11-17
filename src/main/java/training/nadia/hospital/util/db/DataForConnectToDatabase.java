package training.nadia.hospital.util.db;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

public class DataForConnectToDatabase {

    private Properties properties;

    private Logger logger = LogManager.getRootLogger();

    public DataForConnectToDatabase() {
        properties = new Properties();
        readProperties();
    }

    public String getUrl() {
        return properties.getProperty("url");
    }

    public String getUser() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public Driver getDriver() throws SQLException {

        Driver driver = new com.mysql.cj.jdbc.Driver();
        return driver;
    }

    private void readProperties() {

        try (InputStream input = getClass().getResourceAsStream("/jdbc.properties")) {
            properties.load(input);

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
