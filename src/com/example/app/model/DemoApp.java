package com.example.app.model;

import java.util.List;
import java.util.Scanner;

public class DemoApp {
    
    public static void main(String[] args){
    }
}
  System.out.println("1. Create new Programmer");
        System.out.println("2. Delete existing Programmer");
        System.out.println("3. View all Progarammers");
        System.out.println("4. Exit");
        System.out.println();
        
        int opt;
        do {
        System.out.print("Enter option: ");
        String line = keyboard.nextLine();
        opt = Integer.parseInt(line);
        
        System.out.println("You chose option " + opt);
        switch(opt){
            case 1: (opt ==1 ){
            System.out.println("Creating Programmer");
            break;
        }
        case 2:(opt ==2 ){
            System.out.println("Deleting Programmer");
            break;
        }
        case 3: (opt ==3 ){
            System.out.println("Viewing Programmer");
            break;
        }
        
        while (opt!= 4);
        System.out.println("Goodbye");
        
        
    

