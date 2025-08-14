package Start;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Database.CustomConnection;
import Features.Features;
import Subscription.Subscription;
import Utilities.CommonTools;
import Utilities.CustomValidator;
import Utilities.View;

public class Start {
    public void initialize() {
        CustomConnection connection = new CustomConnection();
        Features features = new Features();
        CustomValidator validator = new CustomValidator();
        View view = new View();
        CommonTools tools = new CommonTools();

        String choice;
        boolean continueLoop = true;

        List<Subscription> subscriptions = new ArrayList<Subscription>();

        subscriptions = connection.connect(subscriptions);
        
        Scanner sc = new Scanner(System.in);
        do {
            view.homeView();
            try {
                choice = sc.nextLine();
                choice = tools.removeSpace(choice);
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
                                choice = tools.removeSpace(choice);
                                validator.validateScannerNumberChoice(choice);
                                validator.validateChoiceNumberRange(choice, 1, 4);
                                continueLoop = false;

                                subscriptions = features.registerMemberHandler(choice, subscriptions);
                            } catch (Exception e) {
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
                        String toSearch = view.targetMemberView(sc);
                        features.searchMember(subscriptions, toSearch);
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
                        String toDeactivate = view.targetMemberView(sc);
                        subscriptions = features.deactivateMember(subscriptions, toDeactivate);
                        System.out.print("Press any key to continue...");
                        sc.nextLine();
                        break;
                    case "6":
                        System.out.println("See you next time!");
                        System.out.println("System shutting down...");
                    default:
                        break;
                }
            } catch (Exception e) {
                continueLoop = true;
                System.out.println("Error: " + e.getMessage());
                System.out.print("Press any key to continue...");
                sc.nextLine();
            } 
        } while (continueLoop);
        sc.close();
    }
}
