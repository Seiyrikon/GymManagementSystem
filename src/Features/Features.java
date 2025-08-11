package Features;

import Exception.InvalidChoiceException;
import Subscription.Monthly;
import Subscription.Subscription;
import Subscription.Weekly;
import Subscription.Yearly;

public class Features {
    public void registerMember(int choice, String id ,String uniqueIdentifier, String memberName, String dateOfAvailment, String membershipExpirationDate, String membershipType, String membershipStatus) throws InvalidChoiceException{
        switch (choice) {
            case 1:
                Subscription yearly = new Yearly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus);
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
                throw new InvalidChoiceException("Registration only accepts choices 1, 2, and 3.");
        }
    }
}
