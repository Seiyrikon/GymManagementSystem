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
            view.homeView();
            try {
                choice = sc.nextLine();
                validator.validateScannerNumberChoice(choice);
                validator.validateChoiceNumberRange(choice, 1, 6);
                continueLoop = false;

                switch (choice) {
                    case "1":
                        continueLoop = true;
                        while(continueLoop) {
                            view.registrationView();
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
                    case "3":
                        continueLoop = true;
                        view.searchView();
                        String name = sc.nextLine();
                        features.searchMember(subscriptions, name);
                        System.out.print("Press any key to continue...");
                        sc.nextLine();
                        break;
                    case "4":
                        continueLoop = true;
                        view.filterView();
                        features.filterActiveMembers(subscriptions);
                        System.out.println("Development in progress...");
                        sc.nextLine();
                        break;
                    case "5":
                        System.out.println("Development in progress...");
                        break;
                    case "6":
                        System.out.println("See you next time!");
                        System.out.println("System shutting down...");
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
