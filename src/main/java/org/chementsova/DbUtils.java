package org.chementsova;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DbUtils {

    public static Connection connect(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }

        FileInputStream fileInputStream;

        Properties prop = new Properties();

        String dbURL;

        String dbPassword;

        String dbUsername;

        try {
           fileInputStream = new FileInputStream("src/main/resources/config.properties");
           prop.load(fileInputStream);
           dbURL = prop.getProperty("db.host");
           dbUsername = prop.getProperty("db.username");
           dbPassword = prop.getProperty("db.password");
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

       Connection connection;

       try {
           connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

       return connection;
    }
}
