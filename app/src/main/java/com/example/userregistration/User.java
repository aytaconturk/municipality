package com.example.userregistration;

public class User {

    private String name;
    private String surname;
    private String TC;
    private String phone;
    private String email;
    private String password;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String surname, String TC, String phone, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.TC = TC;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTC() {
        return TC;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
