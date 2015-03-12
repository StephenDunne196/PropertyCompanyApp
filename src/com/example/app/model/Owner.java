package com.example.app.model;

public class Owner {

    private int id;
    private String fName;
    private String lName;
    private String email;
    private int number;

    public Owner(int id, String fn,String ln, String e, int nm) {
        this.id = id;
        this.fName = fn;
        this.lName = ln;
        this.email = e;
        this.number = nm;
    }

    public Owner(String fn,String ln, String e, int nm) {
        this(-1,fn,ln,e,nm);
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fName;
    }

    public String getLname() {
        return lName;
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

    public void setFname(String fName) {
        this.fName = fName;
    }
    public String setLname() {
        return lName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}