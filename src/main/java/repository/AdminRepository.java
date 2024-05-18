package repository;

import model.Admin;
import model.KonkurimetDataFromDbDto;
import model.dto.Admin.*;
import model.filter.DepartmantByAfatFilter;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public static ArrayList<KonkurimetDataFromDbDto> getAllKonkurimetPerAfat(int afatId) {
        System.out.println("U Ekzekutu get All Konkurimet per afat");
        Connection conn = DBConnector.getConnection();
        String query = "SELECT * FROM vwKonkurrimetData WHERE afatId = ?";

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, afatId);
            ResultSet result = pst.executeQuery();
            return getKonkurimetDataFromResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static ArrayList<KonkurimetDataFromDbDto> getKonkurimetDataFromResultSet(ResultSet result) {
        ArrayList<KonkurimetDataFromDbDto> array = new ArrayList<>();
        System.out.println("U ekzekutu get KonkurimetDataFromResultSet");
        try {
            while (result.next()) {
                int userId = result.getInt("userId");
                String emri = result.getString("emri");
                String mbiemri = result.getString("mbiemri");
                int suksesiKl10 = result.getInt("suksesiKl10");
                int suksesiKl11 = result.getInt("suksesiKl11");
                int suksesiKl12 = result.getInt("suksesiKl12");
                int piketMat = result.getInt("piketMat");
                int piketGjSh = result.getInt("piketGjSh");
                int piketAng = result.getInt("piketAng");
                int piketZgjedhore = result.getInt("piketZgjedhore");
                int piketPranues = result.getInt("piketPranues");
                String deptIdPrioriteti1 = result.getString("deptIdPrioriteti1");
                String deptIdPrioriteti2 = result.getString("deptIdPrioriteti2");
                String deptIdPrioriteti3 = result.getString("deptIdPrioriteti3");
                String deptIdPrioriteti4 = result.getString("deptIdPrioriteti4");
                int afatId = result.getInt("afatId");
                int mbikqyresiId = result.getInt("mbikqyresiId");
                String niveli = result.getString("niveli");
                boolean minoritet = result.getBoolean("minoritet");

                array.add(new KonkurimetDataFromDbDto(userId, emri, mbiemri, suksesiKl10, suksesiKl11, suksesiKl12, piketMat,
                        piketGjSh, piketAng, piketZgjedhore, piketPranues, deptIdPrioriteti1, deptIdPrioriteti2,
                        deptIdPrioriteti3, deptIdPrioriteti4, afatId, mbikqyresiId,niveli,minoritet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }


    public static DepartmentAcceptanceDto getAcceptance(GetAcceptanceDto data) {
        String query = DepartmantByAfatFilter.buildQuery(data.getHera());
        Connection connection = DBConnector.getConnection();

        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, data.getNiveli());
            ResultSet result = pst.executeQuery();
            return getDepartmentAcceptanceFromResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static DepartmentAcceptanceDto getDepartmentAcceptanceFromResultSet(ResultSet result) {
        int IKSNormal = 0;
        int IKSMinoritet = 0;
        int EARNormal = 0;
        int EARMinoritet = 0;
        int EENormal = 0;
        int EEMinoritet = 0;
        int TIKNormal = 0;
        int TIKMinoritet = 0;

        try {
            while (result.next()) {
                String emri = result.getString("emri");
                int nrStudentaveAfat = result.getInt("nrStudentaveAfat");
                int nrStudentaveMinoritetAfat = result.getInt("nrStudentaveMinoritetAfat");

                switch (emri) {
                    case "IKS":
                        IKSNormal = nrStudentaveAfat;
                        IKSMinoritet = nrStudentaveMinoritetAfat;
                        break;
                    case "EAR":
                        EARNormal = nrStudentaveAfat;
                        EARMinoritet = nrStudentaveMinoritetAfat;
                        break;
                    case "EE":
                        EENormal = nrStudentaveAfat;
                        EEMinoritet = nrStudentaveMinoritetAfat;
                        break;
                    case "TIK":
                        TIKNormal = nrStudentaveAfat;
                        TIKMinoritet = nrStudentaveMinoritetAfat;
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DepartmentAcceptanceDto(IKSNormal, IKSMinoritet, EARNormal, EARMinoritet, EENormal, EEMinoritet, TIKNormal, TIKMinoritet);
    }
}








