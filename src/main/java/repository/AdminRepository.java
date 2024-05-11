package repository;

import model.Admin;
import model.dto.Admin.ChangePasswordOnDb;
import model.dto.Admin.EditAdminProfileDto;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            e.printStackTrace();
            return null;
        }


    }

    private static Admin getAdminFromResultSet(ResultSet result){
        try{
            int id = result.getInt("adminId");
            String firstName = result.getString("emri");
            String lastName = result.getString("mbiemri");
            String email = result.getString("email");
            String salt = result.getString("salt");
            String PasswodHash = result.getString("passwordHash");

            return new Admin(id,firstName,lastName,email,salt,PasswodHash);
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
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean savePersonalDetails(EditAdminProfileDto data) {
        String query = "UPDATE tblAdmin SET Emri = ?, Mbiemri = ?, Email = ? WHERE email = ?";

        Connection connection = DBConnector.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, data.getFirstname());
            pst.setString(2, data.getLastName());
            pst.setString(3, data.getEmail());
            pst.setString(4, data.getOldEmail());
            int rowsAffected = pst.executeUpdate();

            pst.close();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
