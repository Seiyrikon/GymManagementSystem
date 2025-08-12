package Subscription;

import java.time.LocalDate;

import Interface.Activated;
import Interface.Deactivated;
import Interface.Expired;
import Utilities.CustomDateFormatter;
import Utilities.DateParser;

public abstract class Subscription implements Activated, Deactivated, Expired{
    final String id, uniqueIdentifier;
    String memberName, dateOfAvailment, membershipExpirationDate, membershipType, membershipStatus;

    DateParser dateParser = new DateParser();
    CustomDateFormatter dateFormatter = new CustomDateFormatter();

    public Subscription(String id, String uniqueIdentifier, String memberName, String dateOfAvailment, String membershipExpirationDate, String membershipType, String membershipStatus) {
        this.id = id;
        this.uniqueIdentifier = uniqueIdentifier;
        setMemberName(memberName);
        setDateOfAvailment(dateOfAvailment);
        setMembershipExpirationDate(membershipExpirationDate);
        setMembershipType(membershipType);
        setMembershipStatus(membershipStatus);
    }

    @Override
    public String toString() {
        return String.format(
            "ID:                 %15s%n" +
            "Unique Identifier:  %15s%n" +
            "Member Name:        %15s%n" +
            "Membership Type:    %15s%n" +
            "Date Of Availment:  %15s%n" +
            "Expiration Date:    %15s%n" +
            "Membership Status:  %15s%n",
            id,
            uniqueIdentifier,
            memberName,
            membershipType,
            dateFormatter.dateFormatterMMMMdyyyy(dateParser.convertToLocalDate(dateOfAvailment)),
            dateFormatter.dateFormatterMMMMdyyyy(dateParser.convertToLocalDate(membershipExpirationDate)),
            membershipStatus
        );
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
    public void activateMemberStatus() {
        setMembershipStatus("Activated");
    }

    @Override
    public void deactivateMemberStatus() {
        setMembershipStatus("Deactivated");
    }

    @Override
    public void isExpiredMemberStatus() {
        DateParser parseDate = new DateParser();
        int currentDate = parseDate.parseDateYYYYMMDDToInt(LocalDate.now().toString());

        if(currentDate > parseDate.parseDateYYYYMMDDToInt(getMembershipExpirationDate())) {
            setMembershipStatus("Expired");
        }
    }
}
