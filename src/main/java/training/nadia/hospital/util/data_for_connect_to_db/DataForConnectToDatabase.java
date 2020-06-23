package training.nadia.hospital.util.data_for_connect_to_db;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
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

    private void readProperties() {

        try (InputStream input = getClass().getResourceAsStream("/jdbc.properties")) {

            properties.load(input);

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
