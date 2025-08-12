package Database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Subscription.Subscription;

public class CustomConnection {
    Database database = new Database();

    public List<Subscription> connect(List<Subscription> subscriptions) {
        return database.readDatabase();
    }
}
