package com.example.app.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private static Model instance = null;

    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }
    
    // this is a comment whats the story

    private List<Programmer> programmers;
    private Model() {

        this.programmers = new ArrayList<Programmer>();

        this.programmers.add(
                new Programmer(
                        "Joe Bloggs", "joe@bloggs.com", "087-6543210",
                        1234, "Java C++ C#", 70000.00));
       
        this.programmers.add(
                new Programmer(
                        "Anne Bloggs", "anne@bloggs.com", "087-7654321",
                        2345, "HTML CSS JavaScript", 75000.00));

        this.programmers.add(
                new Programmer(
                        "Fred Bloggs", "fred@bloggs.com", "087-8765432",
                        3456, "UML JUnit Git", 78000.00));

        this.programmers.add(
                new Programmer(
                        "Mary Bloggs", "mary@bloggs.com", "087-9876543",
                        4567, "PHP MySQL", 80000.00));
    }

    public List<Programmer> getProgrammers() {
        return new ArrayList<Programmer>(this.programmers);
    }

    public void addProgrammer(Programmer p) {
        this.programmers.add(p);
    }
    
    public boolean removeProgrammer(Programmer p) {
        return this.programmers.remove(p);
    }

    public Programmer findProgrammerByStaffNumber(int staffNumber) {
        Programmer p = null;
        int i = 0;
        boolean found = false;
        while (i < this.programmers.size() && !found) {
            p = this.programmers.get(i);
            if (p.getStaffNumber() == staffNumber) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            p = null;
        }
        return p;
    }
}
