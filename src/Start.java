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
                                        break;
                                    case "2":
                                        subscriptions = features.registerMemberHandler(choice, subscriptions);
                                        break;
                                    case "3": 
                                        subscriptions = features.registerMemberHandler(choice, subscriptions);
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
                        subscriptions = features.viewAllMembers(subscriptions);
                        System.out.print("Press any key to continue...");
                        sc.nextLine();
                        break;
                    case "3":
                        continueLoop = true;
                        view.searchView();
                        String nameSearch = sc.nextLine();
                        features.searchMember(subscriptions, nameSearch);
                        System.out.print("Press any key to continue...");
                        sc.nextLine();
                        break;
                    case "4":
                        continueLoop = true;
                        view.filterView();
                        features.filterActiveMembers(subscriptions);
                        System.out.print("Press any key to continue...");
                        sc.nextLine();
                        break;
                    case "5":
                        continueLoop = true;
                        view.deactivateView();
                        String nameDeactivate = sc.nextLine();
                        subscriptions = features.deactivateMember(subscriptions, nameDeactivate);
                        System.out.print("Press any key to continue...");
                        sc.nextLine();
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
