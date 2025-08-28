package Start;

import java.util.Map;
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

        Map<String, Subscription> subscriptionMap = connection.connect();
        Map<String, Subscription> filteredActiveMembers = features.getAllActiveMembers(subscriptionMap);
        
        Scanner sc = new Scanner(System.in);
        do {
            view.homeView();
            try {
                choice = sc.nextLine();
                choice = tools.removeSpace(choice);
                validator.validateScannerNumberChoice(choice);
                validator.validateChoiceNumberRange(choice, 1, 7);
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

                                subscriptionMap = features.registerMemberHandler(choice, subscriptionMap, sc);
                                filteredActiveMembers = features.getAllActiveMembers(subscriptionMap);
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
                        String targetMember = view.targetMemberView(sc);
                        features.editMemberInfo(subscriptionMap, targetMember, sc);
                        System.out.print("Press any key to continue...");
                        sc.nextLine();
                        break;
                    case "3":
                        continueLoop = true;
                        features.viewAllMembers(subscriptionMap);
                        System.out.print("Press any key to continue...");
                        sc.nextLine();
                        break;
                    case "4":
                        continueLoop = true;
                        String toSearch = view.targetMemberView(sc);
                        Subscription searchedMember = features.searchMember(subscriptionMap, toSearch);
                        subscriptionMap = features.forReactivation(subscriptionMap, searchedMember, sc);
                        filteredActiveMembers = features.getAllActiveMembers(subscriptionMap);
                        System.out.print("Press any key to continue...");
                        sc.nextLine();
                        break;
                    case "5":
                        continueLoop = true;
                        view.filterView();
                        features.filterActiveMembers(filteredActiveMembers);
                        System.out.print("Press any key to continue...");
                        sc.nextLine();
                        break;
                    case "6":
                        continueLoop = true;
                        String toDeactivate = view.targetMemberView(sc);
                        subscriptionMap = features.deactivateMember(subscriptionMap, toDeactivate);
                        filteredActiveMembers = features.removeDeactivatedMember(filteredActiveMembers, toDeactivate);
                        System.out.print("Press any key to continue...");
                        sc.nextLine();
                        break;
                    case "7":
                        System.out.println("See you next time!");
                        System.out.println("System shutting down...");
                        break;
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
