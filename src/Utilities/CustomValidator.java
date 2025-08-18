package Utilities;

import java.util.Map;

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

    public void existingSubscription(Map<String, Subscription> subscriptionMap, String uniqueIdentifier, String memberName) throws ExistingSubscriptionException {
        Subscription existingSubscription = subscriptionMap.get(uniqueIdentifier + "," + memberName.toLowerCase());

        if(existingSubscription != null) {
            throw new ExistingSubscriptionException("Subscription already exist.");
        }
    }
}
