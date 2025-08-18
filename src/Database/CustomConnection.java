package Database;

import java.util.Map;

import Subscription.Subscription;

public class CustomConnection {
    Database database = new Database();

    public Map<String, Subscription> connect() {
        return database.readDatabase();
    }
}
