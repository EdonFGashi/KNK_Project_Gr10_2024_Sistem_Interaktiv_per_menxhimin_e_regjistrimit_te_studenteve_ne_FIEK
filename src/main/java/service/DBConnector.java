package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector{


    //Jonit
    private static String url = "jdbc:mysql://localhost:3306/knk_project_gr10_final?";
    private static String user = "root";
    private static String password = "root";
    private static Connection connection = null;

//    public static Connection getConnection() {
//        if(connection == null){
//            try {
//                connection = DriverManager.getConnection(url, user, password);
//            }catch(SQLException e){
//                throw new RuntimeException(e);
//            }
//        }
//
//        //System.out.println("Lidhja u krijua me sukses !");
//        return connection;
//    }

    public static Connection getConnection() {
        if(connection == null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                String unicode="useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8";
                return DriverManager.getConnection("jdbc:mysql://localhost:3306/knk_project_gr10_final?"+unicode, "root", "");
            }catch(Exception ex){
                System.out.println(ex.getMessage());
                System.out.println("couldn't connect!");
                throw new RuntimeException(ex);
            }
        }

        System.out.println("Lidhja u krijua me sukses !");
        return connection;
    }


}
