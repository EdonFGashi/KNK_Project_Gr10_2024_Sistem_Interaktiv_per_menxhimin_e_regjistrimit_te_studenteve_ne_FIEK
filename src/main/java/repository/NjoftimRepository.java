package repository;

import model.Njoftim;
import model.dto.Admin.AddNewNjoftimDto;
import model.filter.NjoftimPagination;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NjoftimRepository {
    public static boolean addNewNjoftim(AddNewNjoftimDto data) {
        Connection conn = DBConnector.getConnection();
        String query = """
                INSERT INTO tblNjoftimet (text, adminId)
                VALUES (?, ?)
                """;

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, data.getText());
            pst.setInt(2, data.getAdminId());

            pst.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception or handle it according to your application's requirements
            return false;
        }
    }


    private static ArrayList<Njoftim> getNjoftimFromResultSet(ResultSet result) {
        ArrayList<Njoftim> array = new ArrayList<>(0);
        try {
            while (result.next()) {
                int njoftimId = result.getInt("njoftimId");
                String text = result.getString("text");
                int adminId = result.getInt("adminId");
                array.add(new Njoftim(njoftimId, text, adminId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return array;
    }

    public static ArrayList<Njoftim> getAllNjoftimArray() {
        Connection conn = DBConnector.getConnection();
        String query = """
                SELECT *
                FROM tblNjoftimet;""";

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            return getNjoftimFromResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean editNjoftim(Njoftim njoftim) {
        Connection connection = DBConnector.getConnection();
        String query = "UPDATE tblNjoftimet SET text = ?, adminId = ? WHERE njoftimId = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, njoftim.getText());
            pst.setInt(2, njoftim.getAdminId());
            pst.setInt(3, njoftim.getNjoftimiId());
            int rowsAffected = pst.executeUpdate();
            pst.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteNjoftim(int id) {
        Connection connection = DBConnector.getConnection();
        String query = "DELETE FROM tblNjoftimet WHERE njoftimId = ?";
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

    public static ArrayList<Njoftim> getPagenatedNjoftim(NjoftimPagination data) {
        Connection conn = DBConnector.getConnection();
        String query = "SELECT * FROM tblNjoftimet WHERE 1 = 1 ";

        query += data.buildQuery();
       // System.out.println(query);

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            return getNjoftimFromResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static int getTotalNjoftimi() {
        Connection conn = DBConnector.getConnection();
        int total = 0;
        String query = "SELECT COUNT(*) FROM tblNjoftimet";

        try {
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

}