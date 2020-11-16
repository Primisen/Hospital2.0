package training.nadia.hospital.util.db.data_for_connect_to_db;

public class DataForConnectToDatabaseFactory {

    private static final DataForConnectToDatabase INSTANCE = new DataForConnectToDatabase();

    private DataForConnectToDatabaseFactory() {
    }

    public static DataForConnectToDatabase getInstance() {
        return INSTANCE;
    }
}
