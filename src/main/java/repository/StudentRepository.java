package repository;

import javafx.scene.image.Image;
import model.RegisteredStudent;
import model.ShkollaMesme;
import model.UserStudent;
import model.dto.Admin.ApproveStudentsDto;
import model.dto.Admin.EditRegisteredStudentDetailsOnDbDto;
import model.dto.ResetPasswordOnDb;
import model.filter.StudentFilter;
import service.DBConnector;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import model.dto.RegisteredStudents.ChartInformationEnrolledStudents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentRepository {
    public static ArrayList<ChartInformationEnrolledStudents> getStudentForChart(String niveli, String gjinia){
        ArrayList<ChartInformationEnrolledStudents> listaInformacionevePerChart = new ArrayList<ChartInformationEnrolledStudents>();

        String query = """ 
                SELECT registered.emriDepartamentit, COUNT(useri.userId) AS total_male_BSC_students 
                FROM tblUserStudent useri 
                JOIN tblRegisteredStudents registered ON useri.userId = registered.userId 
                WHERE useri.gjinia = ? AND registered.niveli = ?
                GROUP BY registered.emriDepartamentit; 
                """;

        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, gjinia);
            pst.setString(2, niveli);
            ResultSet result = pst.executeQuery();
            while(result.next()){
                ChartInformationEnrolledStudents std = getStudentCharInformationForDepartment(result);
                listaInformacionevePerChart.add(std);
            }
        } catch (SQLException e){
            System.out.println("Gabim");
        }
        return listaInformacionevePerChart;
    }

    private static ChartInformationEnrolledStudents getStudentCharInformationForDepartment(ResultSet result) {
        try {
            int numri = result.getInt("total_male_BSC_students");
            String emriDepartamentit = result.getString("emriDepartamentit");
            return new ChartInformationEnrolledStudents(numri, emriDepartamentit);
        } catch (Exception e) {
            System.out.println("Nuk ka te dhenaaaa!");
            return null;
        }
    }

    public static ArrayList<ChartInformationEnrolledStudents> getApplicationsForChart(){
        ArrayList<ChartInformationEnrolledStudents> listaInformacionevePerChart = new ArrayList<ChartInformationEnrolledStudents>();

        String query = "SELECT viti, COUNT(*) AS numri_aplikimeve "
                + "FROM tblAfati "
                + "JOIN tblAplikimi ON tblAplikimi.afatId = tblAfati.afatId "
                + "GROUP BY viti "
                + "ORDER BY viti ASC LIMIT 15; ";
        Connection connection = DBConnector.getConnection();
        try{

            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();

            while(result.next()){
                ChartInformationEnrolledStudents std = getStudentCharInformationForTotalApplications(result);
                listaInformacionevePerChart.add(std);
            }
        } catch (SQLException e){
            System.out.println("Gabim");
        }
        return listaInformacionevePerChart;
    }

    private static ChartInformationEnrolledStudents getStudentCharInformationForTotalApplications(ResultSet result){
        try{
            int numri = result.getInt("numri_aplikimeve");
            int viti = result.getInt("viti");
            return new ChartInformationEnrolledStudents(viti, numri);
        } catch (Exception e){
            System.out.println("Nuk ka te dhenaaaa!");
            return null;
        }
    }




    public static ShkollaMesme getShkollaMeme(int userId) {
        String query = "SELECT * FROM tblShkollaMesme WHERE userId = ? LIMIT 1";
        Connection connection = DBConnector.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, Integer.toString(userId));
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                return getShkollaMesmeFromResultSet(result);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ShkollaMesme getShkollaMesmeFromResultSet(ResultSet result) {
        try {
            int userId = result.getInt("userId");
            String emriShkolles = result.getString("emriShkolles");
            int piketMat = result.getInt("piketMat");
            int piketGjSh = result.getInt("piketGjSh");
            int piketAng = result.getInt("piketAng");
            int piketZgjedhore = result.getInt("piketZgjedhore");
            String lendaZgjedhore = result.getString("lendaZgjedhore");
            int suksesiKl10 = result.getInt("suksesiKl10");
            int suksesiKl11 = result.getInt("suksesiKl11");
            int suksesiKl12 = result.getInt("suksesiKl12");
            byte[] certifikataNotaveBytes = result.getBytes("certifikataNotave");
            byte[] leternjoftimiBytes = result.getBytes("leternjoftimi");
            byte[] diplomashkollesBytes = result.getBytes("diplomashkolles");
            boolean approved = result.getBoolean("approved");

            Image certifikataNotaveImage;
            Image leternjoftimiImage;
            Image diplomashkollesImage;

            if (certifikataNotaveBytes != null) {
                certifikataNotaveImage = new Image(new ByteArrayInputStream(certifikataNotaveBytes));
            } else {
                certifikataNotaveImage = new Image(new FileInputStream("Images/Error404.png"));
            }

            if (leternjoftimiBytes != null) {
                leternjoftimiImage = new Image(new ByteArrayInputStream(leternjoftimiBytes));
            } else {
                leternjoftimiImage = new Image(new FileInputStream("Images/Error404.png"));
            }

            if (diplomashkollesBytes != null) {
                diplomashkollesImage = new Image(new ByteArrayInputStream(diplomashkollesBytes));
            } else {
                diplomashkollesImage = new Image(new FileInputStream("Images/Error404.png"));
            }

            return new ShkollaMesme(userId, emriShkolles, piketMat, piketGjSh, piketAng, piketZgjedhore, lendaZgjedhore, suksesiKl10, suksesiKl11, suksesiKl12, certifikataNotaveImage, leternjoftimiImage, diplomashkollesImage, approved);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<UserStudent> getUserStudents() {
        Connection conn = DBConnector.getConnection();
        String query = "SELECT * FROM tblUserStudent";

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            return getUserStudentFromResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ArrayList<UserStudent> getUserStudentFromResultSet(ResultSet result) {
        ArrayList<UserStudent> array = new ArrayList<>();
        try {
            while (result.next()) {
                int userId = result.getInt("userId");
                String numriPersonal = result.getString("numriPersonal");
                String email = result.getString("email");
                String emri = result.getString("emri");
                String mbiemri = result.getString("mbiemri");
                String nacionaliteti = result.getString("nacionaliteti");
                String qyteti = result.getString("qyteti");
                String shteti = result.getString("shteti");
                String gjinia = result.getString("gjinia");
                Date dataLindjes = result.getDate("dataLindjes");
                String salt = result.getString("salt");
                String passwordHash = result.getString("passwordHash");

                array.add(new UserStudent(userId, numriPersonal, email, emri, mbiemri, nacionaliteti, qyteti, shteti, gjinia, dataLindjes.toString(), salt, passwordHash));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }

    public static RegisteredStudent getRegisteredStudent(int userId) {
        String query = "SELECT * FROM UserStudentRegisteredView WHERE userStudentUserId = ? LIMIT 1";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, userId);
            try (ResultSet result = pst.executeQuery()) {
                if (result.next()) {
                    return getRegisteredStudentFromResultSet(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static RegisteredStudent getRegisteredStudentFromResultSet(ResultSet result) {
        try {
            int userId = result.getInt("userStudentUserId");
            String numriPersonal = result.getString("numriPersonal");
            String email = result.getString("email");
            String emri = result.getString("emri");
            String mbiemri = result.getString("mbiemri");
            String nacionaliteti = result.getString("nacionaliteti");
            String qyteti = result.getString("qyteti");
            String shteti = result.getString("shteti");
            String gjinia = result.getString("gjinia");
            Date dataLindjesDate = result.getDate("dataLindjes");
            String salt = result.getString("salt");
            String passwordHash = result.getString("passwordHash");
            String generatedEmail = result.getString("generatedEmail");
            String registeredEmail = result.getString("generatedId");
            String emriDepartamentit = result.getString("emriDepartamentit");
            String niveli = result.getString("niveli");

            return new RegisteredStudent(userId, numriPersonal, email, emri, mbiemri, nacionaliteti, qyteti, shteti,
                    gjinia, dataLindjesDate.toString(), salt, passwordHash, generatedEmail, registeredEmail, emriDepartamentit,
                    niveli);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean setApprove(ApproveStudentsDto approveStudentsDto) {
        String query = "UPDATE tblShkollaMesme SET approved = ? WHERE userId = ? LIMIT 1";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setBoolean(1, approveStudentsDto.isAprove());
            pst.setInt(2, approveStudentsDto.getId());

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteStudent(int userId) {
        String query = "DELETE FROM tblUserStudent WHERE userId = ?";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, userId);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean resetPassword(ResetPasswordOnDb data) {
        Connection connection = DBConnector.getConnection();
        String query = "UPDATE tblUserStudent SET salt = ?, passwordHash = ? WHERE userId = ?";
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

    public static boolean editRegisteredStudent(EditRegisteredStudentDetailsOnDbDto data) {
        String query = "UPDATE tblRegisteredStudents SET generatedEmail = ?, generatedId = ?, emriDepartamentit = ?, niveli = ? WHERE userId = ?";

        Connection connection = DBConnector.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, data.getGeneratedEmail());
            pst.setString(2, data.getGeneratedId());
            pst.setString(3, data.getDepartmentName());
            pst.setString(4, data.getLevel());
            pst.setInt(5, data.getUserId());
            int rowsAffected = pst.executeUpdate();

            pst.close();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static ArrayList<UserStudent> getFilteredStudents(StudentFilter studentFilter){
        Connection conn = DBConnector.getConnection();
        String query = "SELECT * FROM tblUserStudent WHERE 1 = 1";

        query+=studentFilter.buildQuery();
        System.out.println(query);
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            return getUserStudentFromResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
