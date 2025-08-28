package Utilities;

import java.util.Scanner;

public class View {
    CommonTools tools = new CommonTools();

    public void homeView() {
        System.out.println();
        System.out.println("Welcome to Gym Management System");
        System.out.println();
        System.out.println("(1) Register a member.");
        System.out.println("(2) Edit member information.");
        System.out.println("(3) View all members.");
        System.out.println("(4) Search a member.");
        System.out.println("(5) Filter Active Members.");
        System.out.println("(6) Reactivate a member.");
        System.out.println("(7) Deactivate a member.");
        System.out.println("(8) Exit.");
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

    public String targetMemberView(Scanner sc) {
        boolean emptyUniqueIdentifier = true, emptyName = true;
        String uniqueIdentifier = "", memberName = "";
        System.out.println();

        while(emptyUniqueIdentifier) {
            System.out.print("Enter Unique Identifier: ");
            uniqueIdentifier = sc.nextLine();
            uniqueIdentifier = tools.removeSpace(uniqueIdentifier);

            emptyUniqueIdentifier = tools.inputIsEmpty(uniqueIdentifier);
        }

        while(emptyName) {
            System.out.print("Enter Member Name: ");
            memberName = sc.nextLine();
            memberName = tools.removeSpace(memberName);

            emptyName = tools.inputIsEmpty(memberName);
        }

        System.out.println();

        return uniqueIdentifier + "," + memberName;
    }

    public void filterView() {
        System.out.println();
        System.out.println("All Active Members");
        System.out.println();
    }

    public String editView(Scanner sc) {
        boolean emptyNewMemberName = true;
        String memberName = "";
        System.out.println();
        System.out.println("Edit member information");
        System.out.println();

        while(emptyNewMemberName == true) {
            System.out.print("Enter new Member Name: ");
            memberName = sc.nextLine();
            memberName = tools.removeSpace(memberName);
            emptyNewMemberName = tools.inputIsEmpty(memberName);
        }

        System.out.println();

        return memberName;
    }

    public void currentStatusView() {
            System.out.println("Do you want to reactivate this member?");
            System.out.println();
            System.out.println("(1) Yes.");
            System.out.println("(2) No.");
            System.out.println();
    }

}
