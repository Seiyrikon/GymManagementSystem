package Features;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import Database.Database;
import Subscription.Monthly;
import Subscription.Subscription;
import Subscription.Weekly;
import Subscription.Yearly;
import Utilities.CustomDateFormatter;
import Utilities.DateParser;

public class Features {
    final String YEARLY = "Yearly", MONTHLY = "Monthly", WEEKLY = "Weekly";

    Database database = new Database();
    CustomDateFormatter formatter = new CustomDateFormatter();
    DateParser dateParser = new DateParser();

    public List<Subscription> registerMember(String choice, String id ,String uniqueIdentifier, String memberName, String dateOfAvailment, String membershipExpirationDate, String membershipType, String membershipStatus, List<Subscription> subscriptions) {
        
        switch (choice) {
            case "1":
                Subscription yearly = new Yearly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus);
                subscriptions.add(yearly);
                database.writeDatabase(subscriptions);
                System.out.println("Success!");
                break;
            case "2": 
                Subscription monthly = new Monthly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus);
                subscriptions.add(monthly);
                database.writeDatabase(subscriptions);
                System.out.println("Success!");
                break; 
            case "3":
                Subscription weekly = new Weekly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus);
                subscriptions.add(weekly);
                database.writeDatabase(subscriptions);
                System.out.println("Success!");
            default:
                break;
        }

        return subscriptions;
    }

    public List<Subscription> registerMemberHandler(String choice, List<Subscription> subscriptions) {
        String uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus;

        final String id = (subscriptions.size() == 0) ? "1" : new StringBuilder().append(subscriptions.size() + 1).toString();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Unique Identifier: ");
        uniqueIdentifier = sc.nextLine();

        System.out.print("Enter Member Name: ");
        memberName = sc.nextLine();

        dateOfAvailment = dateParser.removeDash(LocalDate.now().toString());

        switch (choice) {
            case "1":
                membershipExpirationDate = dateParser.removeDash(LocalDate.now().plusYears(1).toString());
                membershipType = YEARLY;
                membershipStatus = "Active";
                subscriptions = registerMember(choice, id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus, subscriptions);
                break;
            case "2": 
                membershipExpirationDate = dateParser.removeDash(LocalDate.now().plusMonths(1).toString());
                membershipType = MONTHLY;
                membershipStatus = "Active";
                subscriptions = registerMember(choice, id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus, subscriptions);
                break;
            case "3":
                membershipExpirationDate = dateParser.removeDash(LocalDate.now().plusWeeks(1).toString());
                membershipType = WEEKLY;
                membershipStatus = "Active";
                subscriptions = registerMember(choice, id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus, subscriptions);
                break;
            default:
                sc.close();
                break;
        }

        return subscriptions;
    }

    public List<Subscription> viewAllMembers(List<Subscription> subscriptions, boolean subscriptionUpdated) {
        
        if(subscriptionUpdated) {
            for(Subscription s : subscriptions) {
                System.out.println(s);
            }
        } else {
            subscriptions = database.readDatabase(subscriptions);

            for(Subscription s : subscriptions) {
                System.out.println(s);
            }
        }

        return subscriptions;
    }
}
