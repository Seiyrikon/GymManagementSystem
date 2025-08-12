package Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Subscription.Monthly;
import Subscription.Subscription;
import Subscription.Weekly;
import Subscription.Yearly;

public class Database {
    final String YEARLY = "Yearly", MONTHLY = "Monthly", WEEKLY = "Weekly";

    public void writeDatabase(List<Subscription> subscriptions) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("database.txt"));
            for(Subscription s : subscriptions) {
                writer.write(s.getId() + ",");
                writer.write(s.getUniqueIdentifier() + ",");
                writer.write(s.getMemberName() + ",");
                writer.write(s.getMembershipType() + ",");
                writer.write(s.getDateOfAvailment() + ",");
                writer.write(s.getMembershipExpirationDate() + ",");
                writer.write(s.getMembershipStatus());
                writer.write("\n");
            }
                writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Subscription> readDatabase() {
        List<Subscription> subscriptions = new ArrayList<Subscription>();
        String data;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("database.txt"));

            while ((data = reader.readLine()) != null) {

                String[] dataParts = data.split(",");

                String id = dataParts[0];
                String uniqueIdentifier = dataParts[1];
                String memberName = dataParts[2];
                String membershipType = dataParts[3];
                String dateOfAvailment = dataParts[4];
                String membershipExpirationDate = dataParts[5];
                String membershipStatus = dataParts[6];

                switch (membershipType) {
                    case YEARLY:
                        subscriptions.add(new Yearly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus));
                        break;
                    case MONTHLY: 
                        subscriptions.add(new Monthly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus));
                        break;
                    case WEEKLY:
                        subscriptions.add(new Weekly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus));
                        break;
                    default:
                        break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Generating database...");
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("database.txt"));
                writer.close();
            } catch (Exception ex) {
            }
        }
        return subscriptions;
    }
} 

