package repository.Supervisor;

import model.dto.Supervisor.SupervisorCreateModelDto;
import model.dto.Supervisor.SupervisorEditDto;
import model.dto.Supervisor.SupervisorTableModel;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            }else {
                connection.close();
                return null;
            }
        } catch (Exception e){
            return null;
        }
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
}
