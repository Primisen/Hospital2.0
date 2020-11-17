package training.nadia.hospital.util.db;

import training.nadia.hospital.util.db.DataForConnectToDatabase;

public class DataForConnectToDatabaseFactory {

    private static final DataForConnectToDatabase INSTANCE = new DataForConnectToDatabase();

    private DataForConnectToDatabaseFactory() {
    }

    public static DataForConnectToDatabase getInstance() {
        return INSTANCE;
    }
}
