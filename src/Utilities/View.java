package Utilities;

public class View {
    public void homeView() {
        System.out.println();
        System.out.println("Welcome to Gym Management System");
        System.out.println();
        System.out.println("(1) Register a member.");
        System.out.println("(2) View all members.");
        System.out.println("(3) Search a member.");
        System.out.println("(4) Filter Active Members.");
        System.out.println("(5) Deactivate a member.");
        System.out.println("(6) Exit.");
        System.out.println();
        System.out.print("Choose: ");
    }

    public void registrationView() {
        System.out.println();
        System.out.println("Type of Subscription");
        System.out.println();
        System.out.println("(1) Yearly");
        System.out.println("(2) Monthly");
        System.out.println("(3) Weekly");
        System.out.println("(4) Back");
        System.out.println();
        System.out.print("Choose: ");
    }

    public void searchView() {
        System.out.println();
        System.out.print("Enter name: ");
    }

    public void filterView() {
        System.out.println();
        System.out.println("All Active Members");
        System.out.println();
    }

    public void deactivateView() {
        System.out.println();
        System.out.print("Enter name: ");
    }
}
