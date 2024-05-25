package repository;

import model.Afat;
import model.dto.Admin.AddNewAfatDto;
import service.DBConnector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AfatRepository {


    public static boolean addNewAfat(AddNewAfatDto addNewAfatDto) {

        Connection conn= DBConnector.getConnection();
        String query = """
                INSERT INTO tblAfati (hera, viti, dataHapjes, dataMbylljes, niveli)
                VALUE (? ,? ,? , ?, ?)
                """;

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, addNewAfatDto.getHera());
            pst.setString(2, Integer.toString(addNewAfatDto.getYear()));
            pst.setString(3, addNewAfatDto.getDataHapjes());
            pst.setString(4, addNewAfatDto.getDataMbylljes());
            pst.setString(5, addNewAfatDto.getNiveli());

            pst.execute();

            return true;
        }catch(Exception e){
            return false;
        }
    }

    public static ArrayList<Afat> getAfatArraySearch(String search){

        Connection conn = DBConnector.getConnection();
        System.out.println("U ekxekutu getFromSearch");

        String query = "SELECT * FROM tblAfati WHERE afatId = ? OR hera LIKE ? OR viti = ? OR dataHapjes LIKE ? OR dataMbylljes LIKE ? OR niveli LIKE ?";

        try {
             PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, search);
            pst.setString(2, search);
            pst.setString(3, search);
            pst.setString(4, "%" + search + "%");
            pst.setString(5, "%" + search + "%");
            pst.setString(6, "%" + search + "%");

            ResultSet result = pst.executeQuery();

           return getAfatiFromResultSet(result);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        }

    private static ArrayList<Afat> getAfatiFromResultSet(ResultSet result) {
        ArrayList<Afat> array = new ArrayList<>(0);
        try {
            while(result.next()) {
                int afatId = result.getInt("afatId");
                String hera = result.getString("hera");
                int viti = result.getInt("viti");
                Date dataHapjes = result.getDate("dataHapjes");
                Date dataMbylljes = result.getDate("dataMbylljes");
                String niveli = result.getString("niveli");
                array.add(new Afat(afatId, viti, hera, dataHapjes.toString(), dataMbylljes.toString(), niveli));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return array;
    }
    public static ArrayList<Afat> getAllAfatArray(){

        Connection conn = DBConnector.getConnection();
        String query =  """
            SELECT *
            FROM tblAfati;""";
      //  System.out.println("U ekzekutu getAllAfatArray");

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            return getAfatiFromResultSet(result);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static boolean editAfat(Afat afat){
        Connection connection = DBConnector.getConnection();
        String query = "UPDATE tblAfati SET hera = ?, viti = ?, dataHapjes = ?, dataMbylljes = ?, niveli = ? WHERE afatId = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, afat.getHera());
            pst.setInt(2, afat.getYear());
            pst.setString(3, afat.getDataHapjes());
            pst.setString(4, afat.getDataMbylljes());
            pst.setString(5, afat.getNiveli());
            pst.setInt(6, afat.getId());
            int rowsAffected = pst.executeUpdate();
            pst.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean deleteAfat(int id){
        Connection connection = DBConnector.getConnection();
        String query = "DELETE FROM tblAfati WHERE afatId = ?";
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

    public static ArrayList<Afat> getAfatArrayByDateAndLevel(String niveli) {

        System.out.println("Executing getAfatArrayByDateAndLevel");

        String query = "SELECT * FROM tblAfati WHERE dataMbylljes > ? AND niveli = ?";

        try {

            Connection conn = DBConnector.getConnection();
            if(conn!=null) System.out.println("U lidh");
            PreparedStatement pst = conn.prepareStatement(query);

            // Set today's date and the level as parameters
            pst.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            pst.setString(2, niveli);

            ResultSet result = pst.executeQuery();

            return getAfatiFromResultSet(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }



    //////////////
    public static Afat getAfatiRegjistrimitForStudent(int userId){
        String query = """
                SELECT DISTINCT tblAfati.*
                FROM tblAfati
                JOIN tblAplikimi ON tblAfati.afatId = tblAplikimi.afatId
                JOIN tblShkollaMesme ON tblAplikimi.shkollaId = tblShkollaMesme.shkollaId
                WHERE tblShkollaMesme.userId = ?;                   
                          """;
        Connection conn = DBConnector.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, userId);
            ResultSet result = pst.executeQuery();
            return getAfatiRegjistrimitForStudentFromResultSte(result);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    private static Afat getAfatiRegjistrimitForStudentFromResultSte(ResultSet result) {
        try {
            while(result.next()) {
                int afatId = result.getInt("afatId");
                String hera = result.getString("hera");
                int viti = result.getInt("viti");
                Date dataHapjes = result.getDate("dataHapjes");
                Date dataMbylljes = result.getDate("dataMbylljes");
                String niveli = result.getString("niveli");
                return new Afat(afatId, viti, hera, dataHapjes.toString(), dataMbylljes.toString(), niveli);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}



