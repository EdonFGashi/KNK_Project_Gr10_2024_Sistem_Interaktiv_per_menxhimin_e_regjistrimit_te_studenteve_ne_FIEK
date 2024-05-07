package repository;

import model.Admin;
import model.dto.Admin.ChangePasswordOnDb;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminRepository {

    public static Admin getByEmail(String email){
        String query = "SELECT * FROM tblAdmin WHERE email = ? LIMIT 1";

        Connection connection = DBConnector.getConnection();

        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1,email);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getAdminFromResultSet(result);
            }
            return null;

        }catch(Exception e){
            return null;
        }
    }

    private static Admin getAdminFromResultSet(ResultSet result){
        try{
            int id = result.getInt("id");
            String firstName = result.getString("firstName");
            String lastName = result.getString("lastName");
            String email = result.getString("email");
            String phone = result.getString("phone");
            String salt = result.getString("salt");
            String passwodHash = result.getString("passwordHash");

            return new Admin(id,firstName,lastName,email,phone, salt,passwodHash);
        }catch(Exception e){
            return null;
        }
    }

    public static boolean changePassword(ChangePasswordOnDb changeData){
        String query = "UPDATE tblAdmin SET passwordHash = ? WHERE email = ?";

        Connection connection = DBConnector.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, changeData.getNewPassword());
            pst.setString(2, changeData.getEmail());

            int rowsAffected = pst.executeUpdate();

            pst.close();
            connection.close();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
