package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    private static final String URL =
        "jdbc:mysql://localhost:3306/library_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "Rahul@2005";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
