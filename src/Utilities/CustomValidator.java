package Utilities;

import java.util.List;

import Exception.ExistingSubscriptionException;
import Exception.InvalidChoiceException;
import Subscription.Subscription;

public class CustomValidator {
    public void validateScannerNumberChoice(String choice) throws InvalidChoiceException {
        try {
            Integer.parseInt(choice);
        } catch (Exception e) {
            throw new InvalidChoiceException("Choice must be a number.");
        }
    }

    public void validateChoiceNumberRange(String choice, int begin, int end) throws InvalidChoiceException {
        if(Integer.parseInt(choice) < begin || Integer.parseInt(choice) > end) {
            throw new InvalidChoiceException("Choice only accepts numbers between " + begin + " and " + end + ".");
        }
    }

    public void existingSubscription(List<Subscription> subscriptions, String uniqueIdentifier, String memberName) throws ExistingSubscriptionException {
        for(Subscription s : subscriptions) {
            if(s.getUniqueIdentifier().equalsIgnoreCase(uniqueIdentifier) && s.getMemberName().equalsIgnoreCase(memberName)) {
                throw new ExistingSubscriptionException("Subscription already exist.");
            }
        }
    }
}
