package com.bridgelabz.restassured;

import java.sql.*;

public class SQLConnector {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
        String dbUrl = "jdbc:mysql://localhost:3306/employee";

        //Database Username
        String username = "root";

        //Database Password
        String password = "Welcome@01";

        //Connection Object
        Connection con = null;

        //Query to Execute
        String query = "select * from emp;";

        //Load mysql jdbc driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Can not find drive in the classpath!", e);
        }

        //Create Connection to DB
        try {
            con = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Connection is successful");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Create Statement Object
        Statement stmt = con.createStatement();

        // Execute the SQL Query. Store results in ResultSet
        ResultSet rs = stmt.executeQuery(query);

        // While Loop to iterate through all data and print results
        while (rs.next()) {
            String myName = rs.getString(1);
            int myAge = rs.getInt(2);
            System.out.println(myName + "  " + myAge);
        }
        // closing DB Connection
        con.close();
    }
}