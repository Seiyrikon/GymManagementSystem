package Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Subscription.Monthly;
import Subscription.Subscription;
import Subscription.Weekly;
import Subscription.Yearly;

public class Database {
    final String YEARLY = "Yearly", MONTHLY = "Monthly", WEEKLY = "Weekly";

    public void writeDatabase(Map<String, Subscription> subscriptionMap) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("database.txt"));
            for(Subscription s : subscriptionMap.values()) {
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
    public Map<String, Subscription> readDatabase() {
        Map<String, Subscription> subscriptionMap = new HashMap<String, Subscription>();
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
                        subscriptionMap.put(uniqueIdentifier + "," + memberName.toLowerCase(), new Yearly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus));
                        break;
                    case MONTHLY: 
                        subscriptionMap.put(uniqueIdentifier + "," + memberName.toLowerCase(), new Monthly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus));
                        break;
                    case WEEKLY:
                        subscriptionMap.put(uniqueIdentifier + "," + memberName.toLowerCase(), new Weekly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus));
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
        return subscriptionMap;
    }
} 

