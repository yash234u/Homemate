package com.example.homemate.Profile;

public class UserModel {
   String address;

    public UserModel() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserModel(String address) {
        this.address = address;
    }
}