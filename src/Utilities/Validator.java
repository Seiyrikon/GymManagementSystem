package Utilities;

import java.util.InputMismatchException;
import java.util.Scanner;

import Exception.InvalidChoiceException;

public class Validator {
    public void validateScannerNumberChoice(Scanner sc) throws InputMismatchException {
        if (!sc.hasNextInt()) {
            throw new InputMismatchException("Choice only accepts numbers.");
        }
    }

    public void validateChoiceNumberRange(int choice, int begin, int end) throws InvalidChoiceException {
        if(choice < begin || choice > end) {
            throw new InvalidChoiceException("Choice only accepts numbers between " + begin + " and " + end + ".");
        }
    }
}
