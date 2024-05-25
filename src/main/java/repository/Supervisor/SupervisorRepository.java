package repository.Supervisor;

import controller.SESSION;
import model.SupervisorTableModel;
import model.dto.Admin.ChangePasswordOnDb;
import model.dto.Admin.ResetPasswordOnDb;
import model.dto.Supervisor.*;
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

    public static boolean editKonkurimi(KonkurimetSaveDto konkurimi) {
        Connection connection = DBConnector.getConnection();
        String query = """
        INSERT INTO tblkonkurimet (aplikimiId, piketPranues, mbikqyresiId)
                VALUES (?, ?, ?)
                ON DUPLICATE KEY UPDATE
                piketPranues = VALUES(piketPranues),
                mbikqyresiId = VALUES(mbikqyresiId);
""";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, konkurimi.getAplikimiId());
            pst.setInt(2, konkurimi.getPiket());
            pst.setInt(3, konkurimi.getSupervisorId());
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

//    private static ArrayList<AplikimetDto> getAplikimetFromResultSet(ResultSet result) {
//        ArrayList<AplikimetDto> array = new ArrayList<>();
//        try {
//            while (result.next()) {
//                int mbikqyresiId = result.getInt("mbikqyresiId");
//                String email = result.getString("email");
//                String emri = result.getString("emri");
//                String mbiemri = result.getString("mbiemri");
//                String salt = result.getString("salt");
//                String passwordHash = result.getString("passwordHash");
//                array.add(new SupervisorTableModel(mbikqyresiId, emri, mbiemri, email,salt, passwordHash));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return array;
//    }


//    public static ArrayList<KonkurimetShowDto> getKonkurimetSearch(String search){
//        ArrayList<KonkurimetShowDto> array = new ArrayList<>();
//
//        int mbikqyresiId = SESSION.getLoggedSupervisor().getMbikqyresiId();
//        int studentiId = Integer.parseInt(search);
//        int aplikimiId = getAplikimiId(search);
//        int piket = 0;
//
//        array.add(new KonkurimetShowDto(mbikqyresiId, studentiId, aplikimiId, piket));
//
//        return array;
//    }

    public static KonkurimetShowDto getKonkurimetSearch(String search){

        int mbikqyresiId = SESSION.getLoggedSupervisor().getMbikqyresiId();
        int studentiId = Integer.parseInt(search);
        int aplikimiId = getAplikimiId(search);
        int piket = getPiket(Integer.toString(aplikimiId));

        KonkurimetShowDto konkurimet = new KonkurimetShowDto(mbikqyresiId, studentiId, aplikimiId, piket);

//        array.add(new KonkurimetShowDto(mbikqyresiId, studentiId, aplikimiId, piket));

        return konkurimet;
    }

    public static int getAplikimiId(String search){
        Connection conn = DBConnector.getConnection();

        String query = """ 
                SELECT a.aplikimiId
                FROM tbluserstudent u
                JOIN tblshkollamesme s ON u.userId = s.userId
                JOIN tblaplikimi a ON s.shkollaId = a.shkollaId
                WHERE u.userId = ? """;

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, search);

            ResultSet result = pst.executeQuery();

            while (result.next()) {
                int aplikimiId = result.getInt("aplikimiId");
                return aplikimiId;
            }

        }
            catch (Exception e){
                e.printStackTrace();
            }

            return 0;
        }

    public static ArrayList<KonkurimetShowDto> getAllKonkurimetArray() {

        Connection conn = DBConnector.getConnection();
        int mbikqyresiId = SESSION.getLoggedSupervisor().getMbikqyresiId();
        String query = "select * from tblkonkurimet where mbikqyresiId = ?;";

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, mbikqyresiId);
            ResultSet result = pst.executeQuery();
            return getKonkurimetFromResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ArrayList<KonkurimetShowDto> getKonkurimetFromResultSet(ResultSet result) {
        ArrayList<KonkurimetShowDto> array = new ArrayList<>();
        try {
            while (result.next()) {
                int mbikqyresiId = result.getInt("mbikqyresiId");
                int aplikimiId = result.getInt("aplikimiId");
                int piket = result.getInt("piketPranues");

                int idStudenti = getStudentiId(Integer.toString(aplikimiId));

                array.add(new KonkurimetShowDto(aplikimiId, idStudenti, piket, mbikqyresiId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }

    public static int getStudentiId(String aplikimiId){
        Connection conn = DBConnector.getConnection();

        String query = """ 
    SELECT u.userId
    FROM tbluserstudent u
    JOIN tblshkollamesme s ON u.userId = s.userId
    JOIN tblaplikimi a ON s.shkollaId = a.shkollaId
    WHERE a.aplikimiId = ? """;

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, aplikimiId);

            ResultSet result = pst.executeQuery();

            while (result.next()) {
                int studentId = result.getInt("userId");
                return studentId;
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    public static int getPiket(String aplikimiId){
        Connection conn = DBConnector.getConnection();

        String query = """ 
    
            SELECT piketPranues
            FROM tblkonkurimet
            WHERE aplikimiId = ?;""";

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, aplikimiId);

            ResultSet result = pst.executeQuery();

            while (result.next()) {
                int piket = result.getInt("piketPranues");
                return piket;
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }


    public static boolean addSupervisorFromSems(SupervisorTableModel newSupervisorFromSems) {
        Connection connection = DBConnector.getConnection();
        String query = "INSERT INTO tblMbikqyresi (email, emri, mbiemri, salt, passwordHash) VALUES  (?,?,?,?,?)";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, newSupervisorFromSems.getEmail());
            pst.setString(2, newSupervisorFromSems.getFirstName());
            pst.setString(3, newSupervisorFromSems.getLastName());
            pst.setString(4, newSupervisorFromSems.getSalt());
            pst.setString(5, newSupervisorFromSems.getPasswordHash());
            int rowsAffected = pst.executeUpdate();
            pst.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
