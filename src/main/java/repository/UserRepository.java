package repository;
import model.User;
import model.dto.Overall.CreateUserDto;
import model.dto.Student.ApplicationStatusDto;
import model.dto.Student.EditUserProfileDto;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository {

    public static boolean create(CreateUserDto userData)throws SQLException {
        Connection conn = DBConnector.getConnection();
        String query = """
                INSERT INTO tblUser (username, email, salt, passwordHash)
                VALUE (?, ?, ?, ?)
                """;
        try{
            System.out.println("try");
            PreparedStatement pst = conn.prepareStatement(query);
            System.out.println("query");
            pst.setString(1, userData.getUsername());
            pst.setString(2, userData.getEmail());
            pst.setString(3, userData.getSalt());
            pst.setString(4, userData.getPasswordHash());
            System.out.println("Kati");


            pst.execute();
            System.out.println("Katishtu");
            pst.close();
            conn.close();
            System.out.println("Kati u shtu");
            return true;
        }catch (Exception e){
            System.out.println("Nuk u shtu");
            return false;
        }

    }


    public static User getByEmail(String email){
        Connection conn= DBConnector.getConnection();
        String query = "SELECT * FROM tblUser WHERE email = ? LIMIT 1";

        try{
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, email);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                System.out.println("u gjet");
                return getFromResultSet(result);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    private static User getFromResultSet(ResultSet result){
        try{
            System.out.println(4);
            int id = result.getInt("userId");
            String username= result.getString("username");
            String email = result.getString("email");
            String salt = result.getString("salt");
            String passwordHash = result.getString("passwordHash");
            return new User(
                    id, username, email, salt, passwordHash
            );
        }catch (Exception e){
            return null;
        }
    }


    public static boolean savePersonalDetails(EditUserProfileDto data) {
        String query = "UPDATE tblAdmin SET username = ?, email = ? WHERE email = ?";

        Connection connection = DBConnector.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, data.getUsername());
            pst.setString(2, data.getEmail());
            pst.setString(3, data.getOldEmail());
            int rowsAffected = pst.executeUpdate();

            pst.close();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void saveApplicationStatus(ApplicationStatusDto applicationStatus) {
        Connection conn = DBConnector.getConnection();
        String sql = "INSERT INTO tblApplicationStatus (UserID, SubmissionStatus, EditTime, ApplicationName) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, applicationStatus.getUserID());
            pstmt.setString(2, applicationStatus.getSubmissionStatus());
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(applicationStatus.getEditTime()));
            pstmt.setString(4, applicationStatus.getApplicationName());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Nuk u ruajten te dhenat!");
        }
    }
}
