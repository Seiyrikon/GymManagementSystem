import java.util.InputMismatchException;
import java.util.Scanner;

import Database.Database;
import Exception.InvalidChoiceException;
import Features.Features;
import Utilities.Validator;
import Utilities.View;

public class App {
    public static void main(String args[]) throws Exception {

        Features features = new Features();
        Validator validator = new Validator();
        View view = new View();

        int choice = 0;
        boolean isError = true;

        Database database = new Database();
        database.writeDatabase(null);
        
        do {
            Scanner sc = new Scanner(System.in);
            view.Home();
            try {
                validator.validateScannerNumberChoice(sc);
                choice = sc.nextInt();
                validator.validateChoiceNumberRange(choice, 1, 6);
                isError = false;

                switch (choice) {
                    case 1:
                        isError = true;
                        view.Registration();
                        while(isError) {
                            try {
                                validator.validateScannerNumberChoice(sc);
                                choice = sc.nextInt();
                                validator.validateChoiceNumberRange(choice, 1, 4);
                                isError = false;

                                switch (choice) {
                                    case 1:
                                        features.registerMemberHandler(choice);
                                    case 2:
                                        features.registerMemberHandler(choice);
                                    case 3: 
                                        features.registerMemberHandler(choice);
                                    case 4:
                                        break;
                                    default:
                                        break;
                                }
                            } catch (InputMismatchException | InvalidChoiceException e) {
                                isError = true;
                                System.out.println("Error: " + e.getMessage());
                                sc.nextLine();
                            }
                        }
                        isError = true;
                        break;
                
                    default:
                        break;
                }
            } catch (InputMismatchException | InvalidChoiceException e) {
                isError = true;
                System.out.println("Error: " + e.getMessage());
                choice = 0;
                sc.nextLine();
            }
        } while (isError);
    }
}