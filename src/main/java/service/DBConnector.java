package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector{


    //Jonit
    private static String url = "jdbc:mysql://localhost:3306/knk_project_gr10";
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
        System.out.println("Lidhja u krijua me sukses !");
        return connection;
    }

}
