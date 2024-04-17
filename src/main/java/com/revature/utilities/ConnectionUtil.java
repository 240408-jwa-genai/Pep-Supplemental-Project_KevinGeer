package com.revature.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class ConnectionUtil {
    
    public static Connection createConnection() throws SQLException{
        SQLiteConfig config = new SQLiteConfig();  
        config.enforceForeignKeys(true); 
        return DriverManager.getConnection("jdbc:sqlite:Project1/Pep-Supplemental-Project_KevinGeer/src/main/resources/planetarium.db", config.toProperties());
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