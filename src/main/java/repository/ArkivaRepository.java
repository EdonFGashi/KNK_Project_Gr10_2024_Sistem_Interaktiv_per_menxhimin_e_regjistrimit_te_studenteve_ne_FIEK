package repository;

import model.Arkiva;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class ArkivaRepository {

    public static boolean arkivoDokumentin(Arkiva arkivimi){

        String query = """
                INSERT INTO tblArkiva (nrSerik, idStudentit, data)
                VALUES (?, ?, ?);
                """;
        Connection connection = DBConnector.getConnection();
        try{
// PreparedStatement pst = conn.prepareStatement(query);
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, arkivimi.getNrSerik());
            pst.setString(2, arkivimi.getIdStudentit());
            pst.setDate(3, java.sql.Date.valueOf(arkivimi.getData()));
            boolean inserted = pst.execute();
            System.out.println("Ü insertua");
            pst.close();
            return inserted;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static Arkiva verifiko(Arkiva arkiva){
        String query = """
                    SELECT * FROM tblArkiva
                    WHERE documentId = ?
                    AND nrSerik = ?
                    AND idStudentit = ?
                    AND data = ?;               
                """;
        Connection connection = DBConnector.getConnection();
        try{
// PreparedStatement pst = conn.prepareStatement(query);
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, arkiva.getDocumentId());
            pst.setString(2, arkiva.getNrSerik());
            pst.setString(3, arkiva.getIdStudentit());
            pst.setDate(4, java.sql.Date.valueOf(arkiva.getData()));
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getArkivaResultSet(result);
            }
//            connection.close();
            return null;
        } catch(Exception e){
            return null;
        }

    }

    private static Arkiva getArkivaResultSet(ResultSet result){
        try{
            int nrRendor = result.getInt("documentId");
            String nrSerik = result.getString("nrSerik");
            String idStudentit = result.getString("idStudentit");
            LocalDate dataLeshimit = result.getDate("data").toLocalDate();
            return new Arkiva(
                    nrRendor, nrSerik, idStudentit, dataLeshimit
            );
        }catch (Exception a){
            return null;
        }
    }


}
