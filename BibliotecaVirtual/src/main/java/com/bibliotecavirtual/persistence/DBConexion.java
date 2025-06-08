package com.bibliotecavirtual.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConexion {

    private static final String URL = "jdbc:mysql://gateway01.us-east-1.prod.aws.tidbcloud.com:4000/biblioteca_virtual";
    private static final String user = "2nrjKr1bzyGsGm5.root";
    private static final String password = "deKZTb4j3LAOhlMK";

    public static Connection establecerConexion() throws SQLException{
        return DriverManager.getConnection(URL, user, password);

    }
}
