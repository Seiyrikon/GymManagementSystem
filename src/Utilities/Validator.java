package Utilities;

import Exception.InvalidChoiceException;

public class Validator {
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
}
