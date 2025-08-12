package Features;

import java.time.LocalDate;
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

    public void registerMember(int choice, String id ,String uniqueIdentifier, String memberName, String dateOfAvailment, String membershipExpirationDate, String membershipType, String membershipStatus) {
        switch (choice) {
            case 1:
                Subscription yearly = new Yearly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus);
                database.writeDatabase(yearly);
                System.out.println(yearly);
                break;
            case 2: 
                Subscription monthly = new Monthly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus);
                System.out.println(monthly);
                break; 
            case 3:
                Subscription weekly = new Weekly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus);
                System.out.println(weekly);
            default:
                break;
        }
    }

    public void registerMemberHandler(int choice) {
        String uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Unique Identifier: ");
        uniqueIdentifier = sc.nextLine();
        System.out.print("Enter Member Name: ");
        memberName = sc.nextLine();
        dateOfAvailment = dateParser.removeDash(LocalDate.now().toString());

        switch (choice) {
            case 1:
                membershipExpirationDate = dateParser.removeDash(LocalDate.now().plusYears(1).toString());
                membershipType = YEARLY;
                membershipStatus = "Active";
                registerMember(choice, "1", uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus);
                break;
                case 2: 
                membershipExpirationDate = dateParser.removeDash(LocalDate.now().plusMonths(1).toString());
                membershipType = MONTHLY;
                membershipStatus = "Active";
                registerMember(choice, "1", uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus);
                break;
                case 3:
                membershipExpirationDate = dateParser.removeDash(LocalDate.now().plusWeeks(1).toString());
                membershipType = WEEKLY;
                membershipStatus = "Active";
                registerMember(choice, "1", uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus);
                break;
            default:
                break;
        }
    }
}
