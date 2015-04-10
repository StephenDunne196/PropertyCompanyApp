package com.example.app.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static Model instance = null;

    public static synchronized Model getInstance() throws dataAccessException {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }
    
    // this is a comment whats the story

    private List<Property> properties;
    private PropertyTableGateway propertyGateway;
    private OwnerTableGateway ownerGateway;
    private List<Owner> Owners;
    
    private Model() throws dataAccessException {
        try {
            Connection conn = DBConnection.getInstance();
            this.propertyGateway = new PropertyTableGateway(conn);
            this.ownerGateway = new OwnerTableGateway(conn);

            this.properties = this.propertyGateway.getProperties();
            this.Owners = this.ownerGateway.getOwners();
        }
        catch (ClassNotFoundException | SQLException ex) {
            throw new dataAccessException("Excepion Initialising Model Object" + ex.getMessage());
        }
    }

    public List<Property> getProperties() {
        return new ArrayList<Property>(this.properties);
    }
    
    public List<Property> getPropertiesByOwnerId(int ownerId) {
       List<Property> list =  new ArrayList<Property>();
       for (Property p : this.properties){
           if(p.getOwnerId() == ownerId) {
               list.add(p);
            }
           }
       return list;
       }
    

    public boolean addProperties( Property p) throws dataAccessException {
        boolean result = false;
        try {
            int id = this.propertyGateway.insertProperty(
                    p.getName(), p.getAddress(), p.getDescription(),
                    p.getRent(), p.getBedrooms(),p.getOwnerId()
            );
            if (id != -1) {
                p.setId(id);
                this.properties.add(p);
                result = false;
            }
        }
        catch (SQLException ex) {
             throw new dataAccessException("Excepion adding Property: " + ex.getMessage());
        }
        return result;
    }
    
    public boolean deleteProperty(Property p) throws dataAccessException {
        boolean removed = false;

        try {
            removed = this.propertyGateway.deleteProperty(p.getPropertyID());
            if (removed) {
                removed = this.properties.remove(p);
            }
        }
        catch (SQLException ex) {
            throw new dataAccessException("Excepion removing Property" + ex.getMessage());
        }

        return removed;
    }
    
    boolean updateProperty(Property p) throws dataAccessException {
        boolean updated = false;

        try {
            updated = this.propertyGateway.updateProperty(p);
        }
        catch (SQLException ex) {
             throw new dataAccessException("Excepion editing Property" + ex.getMessage());
        }

        return updated;
    }

    public Property findPropertyByPropertyID(int PropertyID) {
        Property p = null;
        int i = 0;
        boolean found = false;
        while (i < this.properties.size() && !found) {
            p = this.properties.get(i);
            if (p.getPropertyID() == PropertyID) {
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

    
    
    public List<Owner> getOwner() {
        return new ArrayList<Owner>(this.Owners);
    }
     public boolean addOwner( Owner o) throws dataAccessException {
        boolean result = false;
        try {
            int id = this.ownerGateway.insertOwner(
                    o.getName(), o.getEmail(), o.getNumber()
                 
            );
            if (id != -1) {
                o.setOwnerId(id);
                this.Owners.add(o);
                result = true;
            }
        }
        catch (SQLException ex) {
            throw new dataAccessException("Excepion Adding Owner" + ex.getMessage());
        }
        return result;
    }
    
     public Owner findOwnerById(int id) {
        Owner o = null;
        int i = 0;
        boolean found = false;
        while (i < this.Owners.size() && !found) {
            o = this.Owners.get(i);
            if (o.getOwnerId() == id) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            o = null;
        }
        return o;
    }
     
     public boolean deleteOwner(Owner o) throws dataAccessException {
        boolean removed = false;

        try {
            removed = this.ownerGateway.deleteOwner(o.getOwnerId());
            if (removed) {
                removed = this.Owners.remove(o);
            }
        }
        catch (SQLException ex) {
            throw new dataAccessException("Excepion deleting Owner" + ex.getMessage());
        }

        return removed;
    }
     
     
     boolean updateOwner(Owner o) throws dataAccessException {
        boolean updated = false;

        try {
            updated = this.ownerGateway.updateOwner(o);
        }
        catch (SQLException ex) {
            throw new dataAccessException("Excepion Editing Owner" + ex.getMessage());
        }

        return updated;
    }

}
