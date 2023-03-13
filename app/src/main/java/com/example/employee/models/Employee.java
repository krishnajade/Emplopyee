package com.example.employee.models;
public class Employee {
    private final int id;
    private String name;
    private final String email;
    private final int age;
    private final String designation;
    private final String created = null;
    private String serverResponse;


    public Employee(int id, String name, String email, int age, String designation) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.designation = designation;

    }
    public int getId() {
        return id;
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
    public int getAge() {
        return age;
    }
    public String getDesignation() {
        return designation;
    }
    public String getCreated() {
        return created;
    }
    public String getServerResponse() {
        return serverResponse;
    }
}