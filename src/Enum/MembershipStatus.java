package Enum;

public enum MembershipStatus {
    ACTIVATED("Activated"), DEACTIVATED("Deactivated"), EXPIRED("Exprired");

    public final String label;

    MembershipStatus(String label) {
        this.label = label;
    }
}
