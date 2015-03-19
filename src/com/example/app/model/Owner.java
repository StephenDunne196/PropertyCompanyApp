package com.example.app.model;

public class Owner {

    private int id;
    private String name;
    private String email;
    private int number;

    public Owner(int id, String n, String e, int nm) {
        this.id = id;
        this.name = n;
     
        this.email = e;
        this.number = nm;
    }

    public Owner(String n, String e, int nm) {
        this(-1,n,e,nm);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    public int getNumber() {
        return number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}