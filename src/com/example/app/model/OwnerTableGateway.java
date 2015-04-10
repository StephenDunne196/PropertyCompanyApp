package com.example.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OwnerTableGateway {

    private Connection mConnection;

    private static final String TABLE_NAME = "owner";
    private static final String COLUMN_ID = "ownerId";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_NUMBER = "Number";

    public OwnerTableGateway(Connection connection) {
        mConnection = connection;
    }

    public int insertOwner(String n, String e, String num) throws SQLException {
        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;
        int id = -1;

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_NAME + ", " +
                COLUMN_EMAIL + ", " +
                COLUMN_NUMBER +
                ") VALUES (?, ?, ?)";

        // create a PreparedStatement object to execute the query and insert the values into the query
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, n);
        stmt.setString(2, e);
        stmt.setString(3, num);

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

    public boolean deleteOwner(int id) throws SQLException {
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

    public List<Owner> getOwners() throws SQLException {
        String query;                   // the SQL query to execute
        Statement stmt;                 // the java.sql.Statement object used to execute the SQL query
        ResultSet rs;                   // the java.sql.ResultSet representing the result of SQL query
        List<Owner> owners;         // the java.util.List containing the Owner objects created for each row
                                        // in the result of the query the id of a owner

        String name, email, number;
        int id;
        Owner m;                   // a Owner object created from a row in the result of the query

        // execute an SQL SELECT statement to get a java.util.ResultSet representing
        // the results of the SELECT statement
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);

        // iterate through the result set, extracting the data from each row
        // and storing it in a Owner object, which is inserted into an initially
        // empty ArrayList
        owners = new ArrayList<Owner>();
        while (rs.next()) {
            id = rs.getInt(COLUMN_ID);
            name = rs.getString(COLUMN_NAME);
            email = rs.getString(COLUMN_EMAIL);
            number = rs.getString(COLUMN_NUMBER);

            m = new Owner(id, name, email, number);
            owners.add(m);
        }

        // return the list of Owner objects retrieved
        return owners;
    }

    boolean updateOwner(Owner m) throws SQLException {
        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_NAME      + " = ?, " +
                COLUMN_EMAIL    + " = ?, " +
                COLUMN_NUMBER + " = ? " +
                " WHERE " + COLUMN_ID + " = ?";

        // create a PreparedStatement object to execute the query and insert the new values into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setString(1, m.getName());
        stmt.setString(2, m.getEmail());
        stmt.setString(3, m.getNumber());
        stmt.setInt(4, m.getOwnerId());

        // execute the query
        numRowsAffected = stmt.executeUpdate();

        // return the true if one and only one row was updated in the database
        return (numRowsAffected == 1);
    }

}