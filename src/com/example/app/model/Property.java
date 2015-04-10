package com.example.app.model;

public class Property implements Comparable<Property> {

    private int id;
    private String name;
    private String address;
    private String description;
    private double rent;
    private int bedrooms;
    private int ownerId;
    

    public Property(int id, String n, String a, String d, double r, int b, int oId){
        this.id = id;
        this.name = n;
        this.address = a;
        this.description = d;
        this.rent = r;
        this.bedrooms = b;
        this.ownerId = oId;
    }
    
    public Property(String n, String a, String d, double r, int b, int oId) {
        this(-1, n, a, d, r, b,oId);
    }

    public int getPropertyID() {
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
    public int getOwnerId() {
      return ownerId;
    }

    public void setOwnerId(int ownerId) {
       this.ownerId = ownerId;
    }

    /*int getPropertyID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/

    @Override
    public int compareTo(Property that){
    String myName = this.getName();
    String yourName= that.getName();
    
    return myName.compareTo(yourName);
    }
}