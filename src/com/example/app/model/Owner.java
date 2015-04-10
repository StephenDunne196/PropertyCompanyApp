package com.example.app.model;

public class Owner {

    private int ownerId;
    private String name;
    private String email;
    private String number;

    public Owner(int id, String n, String e, String num) {
        this.ownerId = id;
        this.name = n;
     
        this.email = e;
        this.number = num;
    }

    public Owner(String n, String e, String num) {
        this(-1,n,e,num);
    }

  

    public int getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    public String getNumber() {
        return number;
    }

    public void setOwnerId(int id) {
        this.ownerId = id;
    }

    public void setName(String name) {
        this.name = name;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}