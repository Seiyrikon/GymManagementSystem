package Features;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Database.Database;
import Enum.MembershipStatus;
import Enum.SubscriptionType;
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

    Database database = new Database();
    CustomDateFormatter formatter = new CustomDateFormatter();
    DateParser dateParser = new DateParser();
    CommonTools tools = new CommonTools();
    CustomValidator validator = new CustomValidator();

    public Map<String, Subscription> registerMember(String choice, String id ,String uniqueIdentifier, String memberName, String dateOfAvailment, String membershipExpirationDate, String membershipType, String membershipStatus, Map<String, Subscription> subscriptionMap) {
        
        switch (choice) {
            case "1":
                Subscription yearly = new Yearly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus);
                subscriptionMap.put(uniqueIdentifier + "," + memberName.toLowerCase(), yearly);
                database.writeDatabase(subscriptionMap);
                System.out.println("Subscription has beem created successfully!");
                break;
            case "2": 
                Subscription monthly = new Monthly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus);
                subscriptionMap.put(uniqueIdentifier + "," + memberName.toLowerCase(), monthly);
                database.writeDatabase(subscriptionMap);
                System.out.println("Subscription has beem created successfully!");
                break; 
            case "3":
                Subscription weekly = new Weekly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus);
                subscriptionMap.put(uniqueIdentifier + "," + memberName.toLowerCase(), weekly);
                database.writeDatabase(subscriptionMap);
                System.out.println("Subscription has beem created successfully!");
            default:
                break;
        }

        return subscriptionMap;
    }

    public Map<String, Subscription> registerMemberHandler(String choice, Map<String, Subscription> subscriptionMap) throws ExistingSubscriptionException {
        if(choice.equals("4")) {
            return subscriptionMap;
        }

        String uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus;

        final String id = (subscriptionMap.size() == 0) ? "1" : new StringBuilder().append(subscriptionMap.size() + 1).toString();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Unique Identifier: ");
        uniqueIdentifier = sc.nextLine();
        uniqueIdentifier = tools.removeSpace(uniqueIdentifier);

        System.out.print("Enter Member Name: ");
        memberName = sc.nextLine();
        memberName = tools.removeSpace(memberName);

        validator.existingSubscription(subscriptionMap, uniqueIdentifier, memberName);

        dateOfAvailment = dateParser.removeDash(LocalDate.now().toString());

        switch (choice) {
            case "1":
                membershipExpirationDate = dateParser.removeDash(LocalDate.now().plusYears(1).toString());
                membershipType = SubscriptionType.YEARLY.label;
                membershipStatus = MembershipStatus.ACTIVATED.label;
                subscriptionMap = registerMember(choice, id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus, subscriptionMap);
                break;
            case "2": 
                membershipExpirationDate = dateParser.removeDash(LocalDate.now().plusMonths(1).toString());
                membershipType = SubscriptionType.MONTHLY.label;
                membershipStatus = MembershipStatus.ACTIVATED.label;
                subscriptionMap = registerMember(choice, id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus, subscriptionMap);
                break;
            case "3":
                membershipExpirationDate = dateParser.removeDash(LocalDate.now().plusWeeks(1).toString());
                membershipType = SubscriptionType.WEEKLY.label;
                membershipStatus = MembershipStatus.ACTIVATED.label;
                subscriptionMap = registerMember(choice, id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus, subscriptionMap);
                break;
            default:
                sc.close();
                break;
        }

        return subscriptionMap;
    }

    public void viewAllMembers(Map<String, Subscription> subscriptionMap) {
        
        if(subscriptionMap.size() != 0) {
            for(Subscription s : subscriptionMap.values()) {
                System.out.println(s);
            }
        } else {
            System.out.println("No member/s yet.");
        }
    }

    public void searchMember(Map<String, Subscription> subscriptionMap, String toSearch) throws SubscriptionNotFoundException {
        String[] parts = toSearch.split(",");
        Subscription searchedMember = subscriptionMap.get(toSearch);

        if(searchedMember != null) {
            System.out.println(searchedMember);
        } else {
            throw new SubscriptionNotFoundException("Member \"" + parts[1] + "\" not found.");
        }
    }

    public void filterActiveMembers(Map<String, Subscription> activeMembers) {
        if(activeMembers.size() != 0) {
            for(Subscription s : activeMembers.values()) {
                System.out.println(s);
            }
        } else {
            System.out.println("No active member/s yet.");
        }
    }

    public Map<String, Subscription> deactivateMember(Map<String, Subscription> subscriptionMap, String toDeactivate) throws SubscriptionNotFoundException {
        String[] parts = toDeactivate.split(",");
        Subscription targetMember = subscriptionMap.get(toDeactivate);

        if(targetMember != null) {
            targetMember.deactivateMemberStatus();
            database.writeDatabase(subscriptionMap);
            System.out.println("Deactivation Successful!");
        } else {
            throw new SubscriptionNotFoundException("Member \"" + parts[1] + "\" not found.");
        }

        return subscriptionMap;
    }

    public Map<String, Subscription> getAllActiveMembers(Map<String, Subscription> subscriptionMap) {
        Map<String, Subscription> activeMembers = new HashMap<String, Subscription>();
        for(Map.Entry<String, Subscription> s: subscriptionMap.entrySet()) {
            Subscription subbedMember = s.getValue();

            if(subbedMember.getMembershipStatus().equalsIgnoreCase(MembershipStatus.ACTIVATED.label)) {
                activeMembers.put(subbedMember.getUniqueIdentifier() + "," + subbedMember.getMemberName(), subbedMember);
            }
        }
        return activeMembers;
    }

    public Map<String, Subscription> removeDeactivatedMember(Map<String, Subscription> filteredActiveMembers, String toDeactivate) {
        String[] parts = toDeactivate.split(",");

        if(filteredActiveMembers.get(toDeactivate) != null) {
            filteredActiveMembers.remove(toDeactivate);
        } else {
            System.out.println("Member \"" + parts[1] + "\" not found.");
        }

        return filteredActiveMembers;
    }

}
