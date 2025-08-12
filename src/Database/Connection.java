package Database;

import java.util.List;

import Subscription.Subscription;

public class Connection {
    Database database = new Database();

    public List<Subscription> connect(List<Subscription> subscriptions) {
        return database.readDatabase(subscriptions);
    }
}
