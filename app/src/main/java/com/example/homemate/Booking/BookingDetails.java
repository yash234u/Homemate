package com.example.homemate.Booking;

public class BookingDetails {
    private String userEmail;
    private String uniqueOrderId;
    private String serviceDate;
    private String instructions;
    private String servicePrice;
    private String serviceName;
    private String serviceProviderContact;
    private String serviceProvider;
    //private String waiting;
    private String serviceTime;
    private String userAddress;

    // No-argument constructor (required by Firebase)
    public BookingDetails() {
    }

    // Parameterized constructor
    public BookingDetails(String userEmail, String uniqueOrderId, String serviceDate, String instructions, String servicePrice, String serviceName, String serviceProviderContact, String serviceProvider, String serviceTime, String userAddress) {
        this.userEmail = userEmail;
        this.uniqueOrderId = uniqueOrderId;
        this.serviceDate = serviceDate;
        this.instructions = instructions;
        this.servicePrice = servicePrice;
        this.serviceName = serviceName;
        this.serviceProviderContact = serviceProviderContact;
        this.serviceProvider = serviceProvider;
        //this.waiting = waiting;
        this.serviceTime = serviceTime;
        this.userAddress = userAddress;
    }

    // Getter and Setter methods (required by Firebase)
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUniqueOrderId() {
        return uniqueOrderId;
    }

    public void setUniqueOrderId(String uniqueOrderId) {
        this.uniqueOrderId = uniqueOrderId;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceProviderContact() {
        return serviceProviderContact;
    }

    public void setServiceProviderContact(String serviceProviderContact) {
        this.serviceProviderContact = serviceProviderContact;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

   /* public String getWaiting() {
        return waiting;
    }

    public void setWaiting(String waiting) {
        this.waiting = waiting;
    }
*/
    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
