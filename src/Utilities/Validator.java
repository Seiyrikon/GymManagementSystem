package Utilities;

import java.util.InputMismatchException;
import java.util.Scanner;

import Exception.InvalidChoiceException;

public class Validator {
    public void validateScannerNumberChoice(String choice) throws InvalidChoiceException {
        try {
            int isInteger = Integer.parseInt(choice);
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
