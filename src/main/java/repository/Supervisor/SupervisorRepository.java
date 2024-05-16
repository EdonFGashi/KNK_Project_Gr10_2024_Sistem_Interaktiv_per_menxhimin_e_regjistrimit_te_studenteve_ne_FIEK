package repository.Supervisor;

import model.SupervisorTableModel;
import model.dto.Admin.ChangePasswordOnDb;
import model.dto.Admin.ResetPasswordOnDb;
import model.dto.Supervisor.SupervisorCreateModelDto;
import model.dto.Supervisor.SupervisorEditDto;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupervisorRepository {
    private static Connection connection() throws SQLException{
        return DBConnector.getConnection();
    }

    public static boolean create(SupervisorCreateModelDto supervisorData) throws SQLException{
        String query = """
                INSERT INTO tblMbikqyresi
                (email, emri, mbiemri, salt, passwordHash) VALUES  (?,?,?,?,?);
                """;

        try{
            PreparedStatement pst = connection().prepareStatement(query);
            pst.setString(1, supervisorData.getEmail());
            pst.setString(2, supervisorData.getFirstName());
            pst.setString(3, supervisorData.getLastName());
            pst.setString(4, supervisorData.getSalt());
            pst.setString(5, supervisorData.getPasswordHash());
            boolean inserted = pst.execute();
            pst.close();
            System.out.println("U regjistrua !!");
            return inserted;
        } catch (Exception e){
            return false;
        }
    }

    public static SupervisorTableModel getSupervisorByEmail(String email){
        String query = """
                SELECT * FROM tblMbikqyresi
                WHERE email = ? LIMIT 1;
                """;
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, email);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getSupervisorFromResultSet(result);
            }
//            Po shkakton problem n'login nese nuk gjendet emaili
//            else {
//                connection.close();
//                return null;
//            }
            return null;
        } catch (Exception e){
            return null;
        }
    }

    public static ArrayList<SupervisorTableModel> getSupervisorsSearch(String search){
            Connection conn = DBConnector.getConnection();

            String query = "SELECT * FROM tblMbikqyresi WHERE mbikqyresiId = ? OR email LIKE ? OR emri LIKE ? OR mbiemri LIKE ?";

            try {
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, search);
                pst.setString(2, "%" + search + "%");
                pst.setString(3, "%" + search + "%");
                pst.setString(4, "%" + search + "%");

                ResultSet result = pst.executeQuery();

                return getSupervisorsFromResultSet(result);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    }

    private static ArrayList<SupervisorTableModel> getSupervisorsFromResultSet(ResultSet result) {
        ArrayList<SupervisorTableModel> array = new ArrayList<>();
        try {
            while (result.next()) {
                int mbikqyresiId = result.getInt("mbikqyresiId");
                String email = result.getString("email");
                String emri = result.getString("emri");
                String mbiemri = result.getString("mbiemri");
                String salt = result.getString("salt");
                String passwordHash = result.getString("passwordHash");
                array.add(new SupervisorTableModel(mbikqyresiId, emri, mbiemri, email,salt, passwordHash));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }

    private static SupervisorTableModel getSupervisorFromResultSet(ResultSet result){
        try{
            int mbikqyresiId = result.getInt("mbikqyresiId");
            String emri = result.getString("emri");
            String mbiemri = result.getString("mbiemri");
            String email = result.getString("email");
            String salt = result.getString("salt");
            String passwordHash = result.getString("passwordHash");
            return new SupervisorTableModel(mbikqyresiId, emri, mbiemri, email, salt, passwordHash);
        } catch (Exception e){
            return null;
        }
    }
    public static boolean editMbikqyresi(SupervisorEditDto mbikqyresi) {
        Connection connection = DBConnector.getConnection();
        String query = "UPDATE tblMbikqyresi SET email = ?, emri = ?, mbiemri = ? WHERE mbikqyresiId = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, mbikqyresi.getEmail());
            pst.setString(2, mbikqyresi.getFirstName());
            pst.setString(3, mbikqyresi.getLastName());
            pst.setInt(4, mbikqyresi.getId());
            int rowsAffected = pst.executeUpdate();
            pst.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean deleteSupervisor(int id) {
        Connection connection = DBConnector.getConnection();
        String query = "DELETE FROM tblMbikqyresi WHERE mbikqyresiId = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            pst.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<SupervisorTableModel> getAllMbikqyresiArray() {
        Connection conn = DBConnector.getConnection();
        String query = "SELECT * FROM tblMbikqyresi";

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            return getSupervisorsFromResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean resetPassword(ResetPasswordOnDb data){
        Connection connection = DBConnector.getConnection();
        String query = "UPDATE tblMbikqyresi SET salt = ?, passwordHash = ? WHERE mbikqyresiId = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, data.getSalt());
            pst.setString(2, data.getPasswordHash());
            pst.setInt(3, data.getId());
            int rowsAffected = pst.executeUpdate();
            pst.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean changePassword(ChangePasswordOnDb changeData){
        String query = "UPDATE tblmbikqyresi SET passwordHash = ? WHERE email = ?";
        System.out.println("Supervisor:");
        System.out.println(changeData.getNewPassword());
        System.out.println(changeData.getEmail());

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
}
