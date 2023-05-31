package com.example.bookstore.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionEstablisher {

    private  static final String user="root";
    private  static final String password="";
    private  static final String uri= "jdbc:mysql://localhost/bookstore";

    public static Connection establishConnection() throws SQLException {

        return DriverManager.getConnection(uri,user,password);
    }
}

