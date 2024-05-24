package repository.Supervisor;

import model.SemsStafModel;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SemsStafRepository {
    public static ArrayList<SemsStafModel> getSemsStafSearch(String search){
        Connection conn = DBConnector.getConnection();

        String query = "SELECT * FROM tblSemsStaf WHERE stafId = ? OR email LIKE ? OR emri LIKE ? OR mbiemri LIKE ? OR facultyId LIKE ?";

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, search);
            pst.setString(2, "%" + search + "%");
            pst.setString(3, "%" + search + "%");
            pst.setString(4, "%" + search + "%");
            pst.setString(5, "%" + search + "%");

            ResultSet result = pst.executeQuery();

            return getSemsStafFromResultSet(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static ArrayList<SemsStafModel> getAllSemsStaf() {
        Connection conn = DBConnector.getConnection();
        String query = "SELECT * FROM tblSemsStaf";

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            return getSemsStafFromResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ArrayList<SemsStafModel> getSemsStafFromResultSet(ResultSet result) {
        ArrayList<SemsStafModel> array = new ArrayList<>();
        try {
            while (result.next()) {
                int stafId = result.getInt("stafId");
                String email = result.getString("email");
                String emri = result.getString("emri");
                String mbiemri = result.getString("mbiemri");
                String salt = result.getString("salt");
                String passwordHash = result.getString("passwordHash");
                int facultyId = result.getInt("facultyId");
                array.add(new SemsStafModel(stafId, email, emri, mbiemri,salt, passwordHash, facultyId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }


}
