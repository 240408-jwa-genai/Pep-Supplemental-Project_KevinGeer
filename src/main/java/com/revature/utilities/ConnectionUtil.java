package com.revature.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    
    public static Connection createConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:sqlite:Project1/Pep-Supplemental-Project_KevinGeer/src/main/resources/planetarium.db");
    }

    public static void main(String[] args) {
        // run this to create database
        try {
            ConnectionUtil.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
