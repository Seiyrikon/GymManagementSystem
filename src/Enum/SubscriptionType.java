package Enum;

public enum SubscriptionType {
    YEARLY("Yearly"), MONTHLY("Monthly"), WEEKLY("Weekly");

    public final String label;

    SubscriptionType(String label) {
        this.label = label;
    }
}
