package Features;

import Subscription.Subscription;
import Subscription.Yearly;

public class Features {
    public void registerMember(int choice, String id ,String uniqueIdentifier, String memberName, String dateOfAvailment, String membershipExpirationDate, String membershipType, String membershipStatus) {
        switch (choice) {
            case 1:
                Subscription yearly = new Yearly(id, uniqueIdentifier, memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus);
                System.out.println(yearly);
                break;

            default:
                break;
        }
    }
}
