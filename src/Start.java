import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Database.CustomConnection;
import Exception.InvalidChoiceException;
import Features.Features;
import Subscription.Subscription;
import Utilities.Validator;
import Utilities.View;

public class Start {
    public void initialize() {
        CustomConnection connection = new CustomConnection();
        Features features = new Features();
        Validator validator = new Validator();
        View view = new View();

        String choice;
        boolean continueLoop = true;
        boolean subscriptionUpdated = false;

        List<Subscription> subscriptions = new ArrayList<Subscription>();

        subscriptions = connection.connect(subscriptions);
        
        Scanner sc = new Scanner(System.in);
        do {
            view.Home();
            try {
                choice = sc.nextLine();
                validator.validateScannerNumberChoice(choice);
                validator.validateChoiceNumberRange(choice, 1, 6);
                continueLoop = false;

                switch (choice) {
                    case "1":
                        continueLoop = true;
                        while(continueLoop) {
                            view.Registration();
                            try {
                                choice = sc.nextLine();
                                validator.validateScannerNumberChoice(choice);
                                validator.validateChoiceNumberRange(choice, 1, 4);
                                continueLoop = false;

                                switch (choice) {
                                    case "1":
                                        subscriptions = features.registerMemberHandler(choice, subscriptions);
                                        subscriptionUpdated = true;
                                        break;
                                    case "2":
                                        subscriptions = features.registerMemberHandler(choice, subscriptions);
                                        subscriptionUpdated = true;
                                        break;
                                    case "3": 
                                        subscriptions = features.registerMemberHandler(choice, subscriptions);
                                        subscriptionUpdated = true;
                                        break;
                                    case "4":
                                        break;
                                    default:
                                        break;
                                }
                            } catch (InvalidChoiceException e) {
                                continueLoop = true;
                                System.out.println("Error: " + e.getMessage());
                                System.out.print("Press any key to continue...");
                                sc.nextLine();
                            }
                        }
                        continueLoop = true;
                        break;
                    case "2": 
                        continueLoop = true;
                        subscriptions = features.viewAllMembers(subscriptions, subscriptionUpdated);
                        subscriptionUpdated = false;
                        System.out.print("Press any key to continue...");
                        sc.nextLine();
                        break;
                    default:
                        break;
                }
            } catch (InvalidChoiceException e) {
                continueLoop = true;
                System.out.println("Error: " + e.getMessage());
                System.out.print("Press any key to continue...");
                sc.nextLine();
            } 
        } while (continueLoop);
        sc.close();
    }
}
