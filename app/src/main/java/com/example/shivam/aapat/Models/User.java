package com.example.shivam.aapat.Models;

/**
 * Created by ishaandhamija on 11/18/18.
 */

public class User {

    String userId;
    String name;
    String contactNumber;
    String bloodGroup;
    Boolean donationWillingness;

    public User() {}

    public User(String userId, String name, String contactNumber, String bloodGroup, Boolean donationWillingness) {
        this.userId = userId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.bloodGroup = bloodGroup;
        this.donationWillingness = donationWillingness;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public Boolean getDonationWillingness() {
        return donationWillingness;
    }
}
