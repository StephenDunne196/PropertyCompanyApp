package com.example.app.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class DemoApp {
    private static final int NAME_ORDER = 1;
    
    private static final int RENT_ORDER = 1;

    
    
    
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Model model;
        int opt = 12;
        Property p;
        do {
            try{
                 model = Model.getInstance();
                    System.out.println("1. Create new Property");
                    System.out.println("2. Delete existing Property");
                    System.out.println("3. Edit existing Property");
                    System.out.println("4. View all Property");
                    System.out.println("5. View all Properties by Rent");
                    System.out.println("6. View single Property");
                    System.out.println();

                    System.out.println("7. Create new Owner");
                    System.out.println("8. Delete existing Owner");
                    System.out.println("9. Edit existing Owner");
                    System.out.println("10. View all Owner");
                    System.out.println("11. View Single Owner");
                     System.out.println();
                    System.out.println("12. Exit");
                    System.out.println();


                    opt = getInt(keyboard,"Enter option: ",10);

                    System.out.println("You chose option " + opt);
                    switch (opt) {
                        case 1: {
                            System.out.println("Creating property");
                            createProperty(keyboard, model);
                            break;
                        }
                        case 2: {
                            System.out.println("Deleting property");
                            deleteProperty(keyboard, model);
                            break;
                        }
                        case 3: {
                            System.out.println("Editing property");
                            editProperty(keyboard, model);
                            break;
                        }
                        case 4: {
                            System.out.println("Viewing property");
                            viewProperty(model, NAME_ORDER);
                            break;
                        }
                        
                         case 5: {
                            System.out.println("Viewing Property By Rent");
                            viewProperty(model, RENT_ORDER);
                            break;
                        }
                        case 6: {
                            System.out.println("Viewing single property");
                            viewPropertySingle(keyboard, model);
                            break;
                        }

                         case 7: {
                            System.out.println("Creating owner");
                            createOwner(keyboard, model);
                            break;
                        }
                        case 8: {
                            System.out.println("Deleting owner");
                            deleteOwner(keyboard, model);
                            break;
                        }
                        case 9: {
                            System.out.println("Editing owner");
                            editOwner(keyboard, model);
                            break;
                        }
                        case 10: {
                            System.out.println("Viewing owner");
                            viewOwner(model);
                            break;
                        }
                         case 11: {
                            System.out.println("Viewing Single owner");
                            viewOwnerSingle(keyboard, model);
                            break;
                        }
                    }
            }
            catch(dataAccessException e){
                System.out.println();
                System.out.println(e.getMessage());
                System.out.println();
            }
    } while (opt != 12);
    }

    private static void createProperty(Scanner keyb, Model mdl) throws dataAccessException {
        Property p = readProperty(keyb);
        if (mdl.addProperties(p)) {
            System.out.println("Property added to database.");
        } else {
            System.out.println("Property not added to database.");
        }
        System.out.println();
    }

    private static void deleteProperty(Scanner keyboard, Model model) throws dataAccessException {
        int ID = getInt(keyboard, "Enter the Property ID of the property to delete:",-1);
        
        Property p;

        p = model.findPropertyByPropertyID(ID);
        if (p != null) {
            if (model.deleteProperty(p)) {
                System.out.println("Property deleted");
            } else {
                System.out.println("Property not deleted");
            }
        } else {
            System.out.println("Property not found");
        }
    }

    private static void editProperty(Scanner kb, Model m) throws dataAccessException {
        int id= getInt(kb, "Enter the Property ID of the property to edit:",-1);
   
        Property p;

        p = m.findPropertyByPropertyID(id);
        if (p != null) {
            editPropertyDetails(kb, p);
            if (m.updateProperty(p)) {
                System.out.println("Property updated");
            } else {
                System.out.println("Property not updated");
            }
        } else {
            System.out.println("Property not found");
        }
    }

    private static void viewProperty(Model mdl, int order) {
        List<Property> property = mdl.getProperties();
        System.out.println();
        if (property.isEmpty()) {
            System.out.println("There are no properties in the database.");
        } else {
            if (order == NAME_ORDER){
                 Collections.sort(property);
            }
            else if (order == RENT_ORDER){
                Comparator<Property> compar =  new PropertyRentComparator();
                 Collections.sort(property,compar);
            }
                
            
         
          displayProperty(property,mdl);
        }
        System.out.println();
    }
    
    private static void displayProperty(List<Property> properties, Model mdl){
          System.out.printf("%5s %20s %20s %15s %8s %8s  %20s \n",
                    "Id", "name", "address", "description", "rent", "bedrooms", "ownerId");
            for (Property pr : properties) {
                Owner o = mdl.findOwnerById(pr.getOwnerId());
                System.out.printf("%5d %20s %20s %15s %8.2f %8d %20s\n",
                        pr.getPropertyID(),
                        pr.getName(),
                        pr.getAddress(),
                        pr.getDescription(),
                        pr.getRent(),
                        pr.getBedrooms(),
                       (o !=null) ? o.getName() : "");

            }
        
        
        
        
    }
    private static void viewPropertySingle(Scanner keyboard, Model model) throws dataAccessException {
        int ID = getInt(keyboard, "Enter the Property ID of the property to delete:",-1);
        
        Property p;

        p = model.findPropertyByPropertyID(ID);
        if (p != null) {
             Owner o = model.findOwnerById(p.getOwnerId());
            System.out.println("Name         : " + p.getName());
            System.out.println();
            System.out.println("Address      : " + p.getAddress());
            System.out.println();
            System.out.println("description  : " + p.getDescription());
            System.out.println();
            System.out.println("Rent         : " + p.getRent());
            System.out.println();
            System.out.println("Bedrooms     : " + p.getBedrooms());
            System.out.println();
            System.out.println("OwnerId      : " + ((o !=null) ? o.getName() : ""));
            System.out.println();
        } else {
            System.out.println("Property not found");
        }
    }

    private static Property readProperty(Scanner keyb) {
        String name, address, description, line;
        int bedrooms, ownerId;
        double rent;

        
        name = getString(keyb, "Enter Name: ");
        System.out.println();
        address = getString(keyb, "Enter Address: ");
        System.out.println();
        description = getString(keyb, "Enter description: ");
       System.out.println();
        rent = getDouble(keyb, "Enter rent: ", 0);
        System.out.println();
        bedrooms = getInt(keyb, "Enter bedrooms: ", 0);
        System.out.println();
        ownerId = getInt(keyb, "Enter Owner id (enter -1 for no owner): ", -1);
        System.out.println();
        Property p
                = new Property(name, address, description,
                        rent, bedrooms,ownerId);

        return p;
    }

    private static void editPropertyDetails(Scanner keyb, Property p) {
        String name, address, description, line;
        int bedrooms, ownerId;
        double rent;
        
        
        name = getString(keyb, "Enter name [" + p.getName() + "]: ");
        address = getString(keyb, "Enter address [" + p.getAddress() + "]: ");
        description = getString(keyb, "Enter Description [" + p.getDescription() + "]: ");
        rent = getDouble(keyb, "Enter Rent [" + p.getRent() + "]: ",0);
        String line2 = getString(keyb, "Enter Bedrooms [" + p.getBedrooms() + "]: ");
        ownerId = getInt(keyb, "Enter Owner id (enter -1 for no owner): ", -1);        
       
        if (name.length() != 0) {
            p.setName(name);
        }
        if (address.length() != 0) {
            p.setAddress(address);
        }
        if (description.length() != 0) {
            p.setDescription(description);
        }
        if (rent != p.getRent()) {
           
            p.setRent(rent);
        }
        if (line2.length() != 0) {
            bedrooms = Integer.parseInt(line2);
            p.setBedrooms(bedrooms);
        }
       if (ownerId != p.getOwnerId()) {
           
            p.setOwnerId(ownerId);
        }
    }
   private static void createOwner(Scanner keyb, Model mdl) throws dataAccessException {
        Owner o = readOwner(keyb);
        if (mdl.addOwner(o)) {
            System.out.println("Owner added to database.");
        } else {
            System.out.println("Owner not added to database.");
        }
        System.out.println();
    }

    private static void deleteOwner(Scanner keyboard, Model model) throws dataAccessException {
        int id = getInt(keyboard,"Enter the Owner ID of the owner to delete:",-1);
        
        Owner o;

        o = model.findOwnerById(id);
        if (o != null) {
            if (model.deleteOwner(o)) {
                System.out.println("Owner deleted");
            } else {
                System.out.println("Owner not deleted");
            }
        } else {
            System.out.println("Owner not found");
        }
    }

    private static void editOwner(Scanner kb, Model m) throws dataAccessException {
        int id = getInt(kb,"Enter the Owner ID of the owner to edit:",-1);
        Owner o;

        o = m.findOwnerById(id);
        if (o != null) {
            editOwnerDetails(kb, o);
            if (m.updateOwner(o)) {
                System.out.println("Owner updated");
            } else {
                System.out.println("Owner not updated");
            }
        } else {
            System.out.println("Owner not found");
        }
    }

    private static void viewOwner(Model mdl) {
        List<Owner> owner = mdl.getOwner();
        System.out.println();
        if (owner.isEmpty()) {
            System.out.println("There are no owners in the database.");
        } else {
            System.out.printf("%5s %20s %20s %15s \n",
                    "Id", "name", "Email", "Number \n");
            for (Owner o : owner) {
                System.out.printf("%5d %20s %20s %15s ",
                        
                        o.getOwnerId(),
                        o.getName(),
                        o.getEmail(),
                        o.getNumber());
                        
                        
                        }
        }
        System.out.println();
    }
    private static void viewOwnerSingle(Scanner keyboard, Model model){
        int id = getInt(keyboard,"Enter the Owner ID of the owner to View:",-1);
        
        Owner o;

        o = model.findOwnerById(id);
        System.out.println();
        if (o != null) {
            System.out.println("Name         : " + o.getName());
            System.out.println();
            
            System.out.println("Email        : " + o.getEmail());
            System.out.println();
            
            System.out.println("Number       : " + o.getNumber());
            System.out.println();
        
        
            List<Property> propertyList = model.getPropertiesByOwnerId(o.getOwnerId());
            if(propertyList.isEmpty()){
                System.out.println();
                System.out.println("This Owner Owns no properties");
                System.out.println();
            }
            else{
                System.out.println("This Owner Owns the following properties");
                System.out.println();
                displayProperty(propertyList,model);
            }
             
            
            
            
          
              }
         else {
               System.out.println("Owner not found");
        }
        
    }

    private static Owner readOwner(Scanner keyb) {
        String name, email, number;
        int ownerId;
        String line;
        
        
       
        name = getString(keyb, "Enter Name: ");
        email = getString(keyb, "Enter Email: ");
        number = getString(keyb, "Enter Number: ");
        line = getString(keyb,"Enter id: ");
        ownerId = Integer.parseInt(line);
        Owner o
                = new Owner(ownerId, name, email, number);

        return o;
    }

    private static void editOwnerDetails(Scanner keyb, Owner o) {
        String name, email, number;
        int id;
        

        name = getString(keyb, "Enter Name [" + o.getName() + "]: ");
        email = getString(keyb, "Enter Email [" + o.getEmail() + "]: ");
        number = getString(keyb, "Enter Number [" + o.getNumber() + "]: ");
        
        if (name.length() != 0) {
            o.setName(name);
        }
        if (email.length() != 0) {
            o.setEmail(email);
        }
        if (number.length() != 0) {
            o.setNumber(number);
        }
       
    }

    private static String getString(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }

    
    private static int getInt(Scanner keyb, String prompt, int defaultValue){
        int opt=defaultValue;
        boolean finished = false;
       do{ 
        try{
            System.out.print(prompt);
            String line = keyb.nextLine();
            if(line.length()>0){
                 opt = Integer.parseInt(line);
            }
            finished = true;
        }
        catch (NumberFormatException e){
            System.out.println("Exception: " + e.getMessage());
        }
       }
       while(!finished);
        return opt;
    }
    
     private static double getDouble(Scanner keyb, String prompt, double defaultValue){
        double opt=defaultValue;
        boolean finished = false;
       do{ 
        try{
            System.out.print(prompt);
            String line = keyb.nextLine();
            if(line.length()>0){
                 opt = Double.parseDouble(line);
            }
            finished = true;
        }
        catch (NumberFormatException e){
            System.out.println("Exception: " + e.getMessage());
        }
       }
       while(!finished);
        return opt;
    }
}
