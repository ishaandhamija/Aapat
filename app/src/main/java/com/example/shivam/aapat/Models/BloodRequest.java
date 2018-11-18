package com.example.shivam.aapat.Models;

/**
 * Created by ishaandhamija on 11/18/18.
 */

public class BloodRequest {

    String bloodRequestId;
    Double latitude;
    Double longitude;
    String bloodGroup;
    String locationAddress;
    String userContactNumber;

    public BloodRequest() {}

    public BloodRequest(String bloodRequestId, Double latitude, Double longitude, String bloodGroup, String locationAddress, String userContactNumber) {
        this.bloodRequestId = bloodRequestId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bloodGroup = bloodGroup;
        this.locationAddress = locationAddress;
        this.userContactNumber = userContactNumber;
    }

    public String getBloodRequestId() {
        return bloodRequestId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public String getUserContactNumber() {
        return userContactNumber;
    }
}
