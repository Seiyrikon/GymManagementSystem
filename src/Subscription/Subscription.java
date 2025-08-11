package Subscription;

import java.time.LocalDate;

import Interface.Activated;
import Interface.Deactivated;
import Interface.Expired;
import Utilities.DateParser;

public abstract class Subscription implements Activated, Deactivated, Expired{
    final String id, uniqueIdentifier;
    String memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus;

    Subscription(String id, String uniqueIdentifier, String memberName, String dateOfAvailment, String membershipExpirationDate, String membershipType, String membershipStatus) {
        this.id = id;
        this.uniqueIdentifier = uniqueIdentifier;
        setMemberName(memberName);
        setDateOfAvailment(dateOfAvailment);
        setMembershipExpirationDate(membershipExpirationDate);
        setMembershipType(membershipType);
        setMembershipStatus(membershipStatus);
    }

    public String getId() {
        return id;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getDateOfAvailment() {
        return dateOfAvailment;
    }

    public void setDateOfAvailment(String dateOfAvailment) {
        this.dateOfAvailment = dateOfAvailment;
    }

    public String getMembershipExpirationDate() {
        return membershipExpirationDate;
    }

    public void setMembershipExpirationDate(String membershipExpirationDate) {
        this.membershipExpirationDate = membershipExpirationDate;
    }

    public String getMembershipType() {
        return membershipType;
    }
    
    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(String membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    @Override
    public void activate() {
        setMembershipStatus("Activated");
    }

    @Override
    public void deactivate() {
        setMembershipStatus("Deactivated");
    }

    @Override
    public void isExpired() {
        DateParser parseDate = new DateParser();
        int currentDate = parseDate.parseDateYYYYMMDDToInt(LocalDate.now().toString());

        if(currentDate > parseDate.parseDateYYYYMMDDToInt(getMembershipExpirationDate())) {
            setMembershipStatus("Expired");
        }
    }
}
