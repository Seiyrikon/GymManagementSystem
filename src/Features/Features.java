package Features;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Database.Database;
import Exception.ExistingSubscriptionException;
import Exception.SubscriptionNotFoundException;
import Subscription.Monthly;
import Subscription.Subscription;
import Subscription.Weekly;
import Subscription.Yearly;
import Utilities.CommonTools;
import Utilities.CustomDateFormatter;
import Utilities.CustomValidator;
import Utilities.DateParser;

public class Features {
    final String YEARLY = "Yearly", MONTHLY = "Monthly", WEEKLY = "Weekly";

    Database database = new Database();
    CustomDateFormatter formatter = new CustomDateFormatter();
    DateParser dateParser = new DateParser();
    CommonTools tools = new CommonTools();
    CustomValidator validator = new CustomValidator();

    public List<Subscription> registerMember(String choice, String id ,String uniqueIdentifier, String memberName, String dateOfAvailment, String membershipExpirationDate, String membershipType, String membershipStatus, List<Subscription> subscriptions) {
        
        switch (choice) {
            case "1":
                Subscription yearly = new Yearly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus);
                subscriptions.add(yearly);
                database.writeDatabase(subscriptions);
                System.out.println("Subscription has beem created successfully!");
                break;
            case "2": 
                Subscription monthly = new Monthly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus);
                subscriptions.add(monthly);
                database.writeDatabase(subscriptions);
                System.out.println("Subscription has beem created successfully!");
                break; 
            case "3":
                Subscription weekly = new Weekly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus);
                subscriptions.add(weekly);
                database.writeDatabase(subscriptions);
                System.out.println("Subscription has beem created successfully!");
            default:
                break;
        }

        return subscriptions;
    }

    public List<Subscription> registerMemberHandler(String choice, List<Subscription> subscriptions) throws ExistingSubscriptionException {
        if(choice.equals("4")) {
            return subscriptions;
        }

        String uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus;

        final String id = (subscriptions.size() == 0) ? "1" : new StringBuilder().append(subscriptions.size() + 1).toString();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Unique Identifier: ");
        uniqueIdentifier = sc.nextLine();
        uniqueIdentifier = tools.removeSpace(uniqueIdentifier);

        System.out.print("Enter Member Name: ");
        memberName = sc.nextLine();
        memberName = tools.removeSpace(memberName);

        validator.existingSubscription(subscriptions, uniqueIdentifier, memberName);

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

    public List<Subscription> viewAllMembers(List<Subscription> subscriptions) {
        
        if(subscriptions.size() != 0) {
            for(Subscription s : subscriptions) {
                System.out.println(s);
            }
        } else {
            System.out.println("No member/s yet.");
        }

        return subscriptions;
    }

    public void searchMember(List<Subscription> subscriptions, String toSearch) throws SubscriptionNotFoundException {
        String[] parts = toSearch.split(",");
        List<Subscription> searchedMember = new ArrayList<Subscription>();
        
        for(Subscription s : subscriptions) {
            if(s.getUniqueIdentifier().equalsIgnoreCase(parts[0]) && s.getMemberName().equalsIgnoreCase(parts[1])) {
                searchedMember.add(s);
                break;
            }
        }

        if(searchedMember.size() != 0) {
            System.out.println(searchedMember.get(0));
        } else {
            throw new SubscriptionNotFoundException("Member \"" + parts[1] + "\" not found.");
        }
    }

    public void filterActiveMembers(List<Subscription> subscriptions) {
        List<Subscription> activeSubscribers = new ArrayList<Subscription>();
        for(Subscription s : subscriptions) {
            if(s.getMembershipStatus().equals("Active")) {
                activeSubscribers.add(s);
            }
        }

        for(Subscription as : activeSubscribers) {
            System.out.println(as);
        }
    }

    public List<Subscription> deactivateMember(List<Subscription> subscriptions, String toDeactivate) throws SubscriptionNotFoundException {
        String[] parts = toDeactivate.split(",");
        for(Subscription s : subscriptions) {
            if(s.getUniqueIdentifier().equalsIgnoreCase(parts[0]) && s.getMemberName().equalsIgnoreCase(parts[1])) {
                s.deactivateMemberStatus();
                database.writeDatabase(subscriptions);
                System.out.println("Deactivation successful!");
                return subscriptions;
            }
        }
        throw new SubscriptionNotFoundException("Member \"" + parts[1] + "\" not found.");
    }

}
