package com.example.app.model;

public class Programmer {

    private String name;
    private String email;
    private String mobile;
    private int staffNumber;
    private String skills;
    private double salary;

    public Programmer(String n, String e, String m, int sn, String sk, double sl) {
        this.name = n;
        this.email = e;
        this.mobile = m;
        this.staffNumber = sn;
        this.skills = sk;
        this.salary = sl;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(int staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
