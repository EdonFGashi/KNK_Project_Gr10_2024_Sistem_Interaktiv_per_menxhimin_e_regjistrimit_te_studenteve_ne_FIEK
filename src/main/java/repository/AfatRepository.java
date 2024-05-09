package repository;

import model.dto.Admin.AddNewAfatDto;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
            pst.close();
            conn.close();
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
