package com.example.homemate.ServiceProvider;

public class SP_Model {
    private String sp_name;
    private Object sp_contact; // Changed to Object
    private String sp_experience;
    private String sp_img;
    private Object sp_ratings; // Changed to Object
    private String sp_services;

    public SP_Model() {
        // Default constructor required for calls to DataSnapshot.getValue(SP_Model.class)
    }

    public SP_Model(String sp_name, Object sp_contact, String sp_services, String sp_experience, Object sp_ratings, String sp_img) {
        this.sp_name = sp_name;
        this.sp_contact = sp_contact;
        this.sp_services = sp_services;
        this.sp_experience = sp_experience;
        this.sp_ratings = sp_ratings;
        this.sp_img = sp_img;
    }

    public String getSp_name() {
        return sp_name;
    }

    public void setSp_name(String sp_name) {
        this.sp_name = sp_name;
    }

    public Object getSp_contact() {
        return sp_contact;
    }

    public void setSp_contact(Object sp_contact) {
        this.sp_contact = sp_contact;
    }

    public String getSp_experience() {
        return sp_experience;
    }

    public void setSp_experience(String sp_experience) {
        this.sp_experience = sp_experience;
    }

    public String getSp_img() {
        return sp_img;
    }

    public void setSp_img(String sp_img) {
        this.sp_img = sp_img;
    }

    public Object getSp_ratings() {
        return sp_ratings;
    }

    public void setSp_ratings(Object sp_ratings) {
        this.sp_ratings = sp_ratings;
    }

    public String getSp_services() {
        return sp_services;
    }

    public void setSp_services(String sp_services) {
        this.sp_services = sp_services;
    }
}
