package com.codecademy.domain;

public class Student {

    private String name;
    private String email;
    private String birthdate;
    private String gender;
    private String postcode;
    private String city;
    private String country;

    public Student(String name, String email, String birthdate, String gender, String postcode, String city, String country) {
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.gender = gender;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    
    
}
