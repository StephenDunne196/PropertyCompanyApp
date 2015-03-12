package com.example.app.model;

import com.example.app.model.Property;
import java.util.List;
import java.util.Scanner;

public class DemoApp {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Model model = Model.getInstance();
        int opt;
        Property p;
        do {
            System.out.println("1. Create new Property");
            System.out.println("2. Delete existing Property");
            System.out.println("3. Edit existing Property");
            System.out.println("4. View all Property");
            System.out.println("5. Exit");
            System.out.println();

            System.out.print("Enter option: ");
            String line = keyboard.nextLine();
            opt = Integer.parseInt(line);

            System.out.println("You chose option " + opt);
            switch (opt) {
                case 1: {
                    System.out.println("Creating property");
                    p = readProperty(keyboard);
                    if(model.addProperty(p)){
                        System.out.println("Property added to database");
                    }
                    else{
                        System.out.println("property not added to database");
                    }
                    System.out.println();
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
                    viewProperty(model);
                    break;
                }
            }
        }
        while (opt != 5);
    }

/*    private static void createProperties(Scanner keyb, Model mdl) {
        Property p = readProperty(keyb);
        if (mdl.addProperties(p)) {
            System.out.println("Property added to database.");
        }
        else {
            System.out.println("Property not added to database.");
        }
        System.out.println();
    }*/

    private static void deleteProperty(Scanner keyboard, Model model) {
        System.out.print("Enter the staff number of the property to delete:");
        int ID = Integer.parseInt(keyboard.nextLine());
        Property p;

        p = model.findPropertyByPropertyID(ID);
        if (p != null) {
            if (model.deleteProperty(p)) {
                System.out.println("Property deleted");
            }
            else {
                System.out.println("Property not deleted");
            }
        }
        else {
            System.out.println("Property not found");
        }
    }


    private static void editProperty(Scanner kb, Model m) {
        System.out.print("Enter the staff number of the property to edit:");
        int id = Integer.parseInt(kb.nextLine());
        Property p;

        p = m.findPropertyByPropertyID(id);
        if (p != null) {
            editPropertyDetails(kb, p);
            if (m.updateProperty(p)) {
                System.out.println("Property updated");
            }
            else {
                System.out.println("Property not updated");
            }
        }
        else {
            System.out.println("Property not found");
        }
    }

    private static void viewProperty(Model mdl) {
        List<Property> property = mdl.getProperty();
        System.out.println();
        if (property.isEmpty()) {
            System.out.println("There are no properties in the database.");
        }
        else {
            System.out.printf("%5s %20s %20s %15s %12s %20s %8s %7s\n",
                    "Id", "name", "address", "description", "rent", "bedrooms");
            for (Property pr : property) {
                System.out.printf("%5d %20s %20s %15s %12d %20s %.2f %7s\n",
                        pr.getId(),
                        pr.getName(),
                        pr.getAddress(),
                        pr.getDescription(),
                        pr.getRent(),
                        pr.getBedrooms());
                     
                 }
        }
        System.out.println();
    }

    private static Property readProperty(Scanner keyb) {
        String name, address, description, line;
        int bedrooms;
        double rent;
      

        name = getString(keyb, "Enter name: ");
        address = getString(keyb, "Enter email: ");
        description = getString(keyb, "Enter mobile: ");
        
        line = getString(keyb, "Enter rent: ");
        rent = Double.parseDouble(line);
        line = getString(keyb, "Enter bedrooms: ");      
        bedrooms = Integer.parseInt(line);
        

        Property p =
                new Property(name, address, description,
                        rent, bedrooms);

        return p;
    }

    private static void editPropertyDetails(Scanner keyb, Property p) {
        String name, address, description, line;
        int bedrooms;
        double rent;
        

        name = getString(keyb, "Enter name [" +  p.getName() + "]: ");
        address = getString(keyb, "Enter address [" +  p.getAddress() + "]: ");
        description = getString(keyb, "Enter Description [" +  p.getDescription() + "]: ");
        String line1 = getString(keyb, "Enter Rent [" +  p.getRent() + "]: ");
        String line2 = getString(keyb, "Enter Bedrooms [" +  p.getBedrooms() + "]: ");
        

        if (name.length() != 0) {
            p.setName(name);
        }
        if (address.length() != 0) {
            p.setAddress(address);
        }
        if (description.length() != 0) {
            p.setDescription(description);
        }
        if (line1.length() != 0) {
            rent = Integer.parseInt(line1);
            p.setRent(rent);
        }
        if (line2.length() != 0) {
            bedrooms=Integer.parseInt(line2);
            p.setBedrooms(bedrooms);
        }
    }

 
    private static String getString(Scanner keyboard, String prompt) {
      System.out.print(prompt);
      return keyboard.nextLine();
    }

}