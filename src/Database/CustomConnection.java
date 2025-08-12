package Database;

import java.util.List;

import Subscription.Subscription;

public class CustomConnection {
    Database database = new Database();

    public List<Subscription> connect(List<Subscription> subscriptions) {
        return database.readDatabase();
    }
}
