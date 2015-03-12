package com.example.app.model;

public class Property {

    private int id;
    private String name;
    private String address;
    private String description;
    private double rent;
    private int bedrooms;
    

    public Property(int id, String n, String a, String d, double r, int b){
        this.id = id;
        this.name = n;
        this.address = a;
        this.description = d;
        this.rent = r;
        this.bedrooms = b;
        
    }

    public Property(String n, String a, String d, double r, int b) {
        this(-1, n, a, d, r, b);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String Address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String mobile) {
        this.description = description;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    int getPropertyID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}