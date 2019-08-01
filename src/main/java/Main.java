package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;
import java.util.Map;
import java.text.MessageFormat;
import java.util.Properties;

import java.sql.*;

public class Main {
    private static String databaseHost = System.getenv("DATABASE_HOST");
    private static String databasePort = System.getenv("DATABASE_PORT");
    private static String databaseName = System.getenv("DATABASE_NAME");
    private static String databaseUser = System.getenv("DATABASE_USER");
    private static String databasePassword = System.getenv("DATABASE_PASSWORD");

    public static void main(String[] args) {
        try {
            Connection conn = database();
        } catch (Exception e){
            System.out.println(e);
        }
        vars();
    }

    public static Connection database() {
        // Create a database connection from config vars
        Connection conn = null;
        String URI = MessageFormat.format("jdbc:mysql://{0}:{1}/{2}", databaseHost, databasePort, databaseName);  // Create db URI
        Properties connectionProps = new Properties();
        connectionProps.put("user", databaseUser);
        connectionProps.put("password", databasePassword);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URI, connectionProps);  // Open db connection
        } catch (Exception e){
            System.out.println(e);
        } finally {
            System.out.println(conn);
        }
        return conn;
    }

    public static void vars() {
        System.out.println("Read Specific Enviornment Variable");
        System.out.println("JAVA_HOME Value:- " + System.getenv("JAVA_HOME"));

        System.out.println("\nRead All Variables:-\n");

        Map <String, String> map = System.getenv();
        for (Map.Entry <String, String> entry: map.entrySet()) {
            System.out.println("Variable Name:- " + entry.getKey() + " Value:- " + entry.getValue());
        }
    }
}
