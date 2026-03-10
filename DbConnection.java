package com.deepak.connection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbConnection {

    public static Connection getConnect() {
        Connection con = null;
        try {
            InputStream is = DbConnection.class.getResourceAsStream("db.properties");
            Properties p = new Properties();
            p.load(is);

            String url  = p.getProperty("jdbc-url");
            String user = p.getProperty("username");
            String pass = p.getProperty("password");

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
