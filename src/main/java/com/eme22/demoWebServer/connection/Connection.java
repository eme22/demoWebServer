package com.eme22.demoWebServer.connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

    private static Connection instance;
    private static java.sql.Connection con;

    private static final String db = "dbdistribuidos";
    private static final String url = "jdbc:mysql://127.0.0.1/"+ db;
    private static final String user = "root";
    private static final String password = "";

    public Connection() throws SQLException, ClassNotFoundException {
        // sqlite driver
        Class.forName("com.mysql.jdbc.Driver");
        // database path, if it's new database, it will be created in the project folder
        con = DriverManager.getConnection(url, user, password);
    }

    public static Connection getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null){
            instance = new Connection();
        }
        return instance;
    }

    public java.sql.Connection getConnection() {
        return con;
    }

    public void close(){
        instance = null;
    }
}
