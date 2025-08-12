package Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Subscription.Subscription;

public class Database {
    public void writeDatabase(Subscription subscription) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("database.txt"));
            writer.write("All Members\n");
            writer.write("Hello");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readDatabase() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("database.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 

