package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector{


    //Jonit
    private static String url = "jdbc:mysql://localhost:3306/knk_Project";
    private static String user = "root";
    private static String password = "";
    private static Connection connection = null;

    public static Connection getConnection() {

        if(connection == null){
            try {
                connection = DriverManager.getConnection(url, user, password);
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

}
