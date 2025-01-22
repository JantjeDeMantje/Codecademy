package com.codecademy.domain;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Student {

    private int studentId;
    private String name;
    private String email;
    private String birthdate;
    private String gender;
    private String zipcode;
    private String city;
    private String country;
    private String address;

    public Student(String name, String email, String birthdate, String gender, String address, String zipcode, String city, String country) {
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
    }

    @SuppressWarnings("exports")
    public Student(int studentId, String name, String email, Date birthdate, String gender,String address, String zipcode, String city, String country) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.birthdate = formatDate(birthdate);
        this.gender = gender;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
        this.address = address;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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


    private String formatDate(Date birthdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(birthdate);
    }

}
