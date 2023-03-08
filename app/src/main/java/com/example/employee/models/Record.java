package com.example.employee.models;

public class Record {
    private String name;
    private String email;
    private int age;
    private String designation;

    public Record(String name, String email, int age, String designation) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.designation = designation;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}

