package com.example.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PropertyTableGateway {

    private Connection mConnection;

    private static final String TABLE_NAME = "property";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_RENT = "rent";
    private static final String COLUMN_BEDROOMS = "bedrooms";
  

    public PropertyTableGateway(Connection connection) {
        mConnection = connection;
    }

    public int insertProperty(String n, String a, String d, double r, int b) throws SQLException {
        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;
        int id = -1;

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_NAME + ", " +
                COLUMN_ADDRESS + ", " +
                COLUMN_DESCRIPTION + ", " +
                COLUMN_RENT + ", " +
                COLUMN_BEDROOMS + " " +
                ") VALUES (?, ?, ?, ?, ?)";

        // create a PreparedStatement object to execute the query and insert the values into the query
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, n);
        stmt.setString(2, a);
        stmt.setString(3, d);
        stmt.setDouble(4, r);
        stmt.setInt(5, b);
    

        // execute the query and make sure that one and only one row was inserted into the database
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
            // if one row was inserted, retrieve the id assigned to that row
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();

            id = keys.getInt(1);
        }

        // return the id assigned to the row in the database
        return id;
    }

    public boolean deleteProperty(int id) throws SQLException {
        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;

        // the required SQL DELETE statement with place holders for the id of the row to be remove from the database
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";

        // create a PreparedStatement object to execute the query and insert the id into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, id);

        // execute the query
        numRowsAffected = stmt.executeUpdate();

        // return the true if one and only one row was deleted from the database
        return (numRowsAffected == 1);
    }

    public List<Property> getProperties() throws SQLException {
        String query;                   // the SQL query to execute
        Statement stmt;                 // the java.sql.Statement object used to execute the SQL query
        ResultSet rs;                   // the java.sql.ResultSet representing the result of SQL query
        List<Property> properties;   // the java.util.List containing the Property objects created for each row
                                        // in the result of the query the id of a programmer

        String name, address, description;
        int id, bedrooms;
        double rent;

        Property p;                   // a Property object created from a row in the result of the query

        // execute an SQL SELECT statement to get a java.util.ResultSet representing
        // the results of the SELECT statement
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);

        // iterate through the result set, extracting the data from each row
        // and storing it in a Property object, which is inserted into an initially
        // empty ArrayList
        properties = new ArrayList<Property>();
        while (rs.next()) {
            id = rs.getInt(COLUMN_ID);
            name = rs.getString(COLUMN_NAME);
            address = rs.getString(COLUMN_ADDRESS);
            description = rs.getString(COLUMN_DESCRIPTION);
            rent = rs.getDouble(COLUMN_RENT);
            bedrooms = rs.getInt(COLUMN_BEDROOMS);
            
            if (rs.wasNull()) {
            
            }

            p = new Property(id, name, address, description, rent, bedrooms);
            properties.add(p);
        }

        // return the list of Property objects retrieved
        return properties;
    }

    boolean updateProperty(Property p) throws SQLException {
        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;
        //int pId;

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_NAME        + " = ?, " +
                COLUMN_ADDRESS     + " = ?, " +
                COLUMN_DESCRIPTION + " = ?, " +
                COLUMN_RENT        + " = ?, " +
                COLUMN_BEDROOMS    + " = ? " +
                " WHERE " + COLUMN_ID + " = ?";

        // create a PreparedStatement object to execute the query and insert the new values into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setString(1, p.getName());
        stmt.setString(2, p.getAddress());
        stmt.setString(3, p.getDescription());
        stmt.setDouble(4, p.getRent());
        stmt.setInt(5, p.getBedrooms());
        stmt.setInt(6, p.getId());

        // execute the query
        numRowsAffected = stmt.executeUpdate();

        // return the true if one and only one row was updated in the database
        return (numRowsAffected == 1);
    }

}