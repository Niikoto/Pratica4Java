/*
package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    static String url = "jdbc:mysql://localhost/Pratica4";
    static String root = "root";
    static String password = "";

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,root,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
*/