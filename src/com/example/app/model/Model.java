package com.example.app.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static Model instance = null;

    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }
    
    // this is a comment whats the story

    private List<Property> properties;
    private PropertyTableGateway propertyGateway;
    
    private Model() {
        try {
            Connection conn = DBConnection.getInstance();
            this.propertyGateway = new PropertyTableGateway(conn);
            //this.managerGateway = new ManagerTableGateway(conn);

            this.properties = this.propertyGateway.getProperties();
            //this.managers = this.managerGateway.getManagers();
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Property> getProperties() {
        return new ArrayList<Property>(this.properties);
    }

    public boolean addProperties( Property p) {
        boolean result = false;
        try {
            int id = this.propertyGateway.insertProperty(
                    p.getName(), p.getAddress(), p.getDescription(),
                    p.getRent(), p.getBedrooms()
            );
            if (id != -1) {
                p.setId(id);
                this.properties.add(p);
                result = true;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean deleteProperty(Property p) {
        boolean removed = false;

        try {
            removed = this.propertyGateway.deleteProperty(p.getId());
            if (removed) {
                removed = this.properties.remove(p);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return removed;
    }
    
    boolean updateProperty(Property p) {
        boolean updated = false;

        try {
            updated = this.propertyGateway.updateProperty(p);
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public 

    boolean addProperty(Property p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Property findPropertyByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    List<Property> getProperty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
