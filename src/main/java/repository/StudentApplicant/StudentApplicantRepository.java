package repository.StudentApplicant;

import controller.SESSION;
import model.dto.Student.AcademicInterestDto;
import model.dto.Student.PersonDTO;
import model.dto.Student.StudentApplicantDto;
import service.DBConnector;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static controller.SESSION.getDeptLevel;
import static controller.SESSION.getLoggedUser;

public class StudentApplicantRepository {

    public static boolean saveData(StudentApplicantDto dto) {
        Connection conn = DBConnector.getConnection();
        if (conn == null) {
            System.out.println("Lidhja me bazën e të dhënave nuk është e mundur.");
            return false;
        }

        String query = "INSERT INTO tblShkollaMesme (userId, emriShkolles, piketMat, piketGjSh, piketAng, piketZgjedhore,lendaZgjedhore,suksesiKl10,suksesiKl11,suksesiKl12,certifikataNotave,leternjoftimi,diplomaShkolles) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, dto.getUserId());
            statement.setString(2, dto.getSchoolName());
            statement.setInt(3, dto.getMathPoints());
            statement.setInt(4, dto.getAlbanianPoints());
            statement.setInt(5, dto.getEnglishPoints());
            statement.setInt(6, dto.getChoosenSubPoints());
            statement.setString(7, dto.getChoosenSub());
            statement.setDouble(8, dto.getSuccesGrade10());
            statement.setDouble(9, dto.getSuccesGrade11());
            statement.setDouble(10, dto.getSuccesGrade12());
            statement.setBytes(11, convertFileToBytes(dto.getFileCertificate()));
            statement.setBytes(12, convertFileToBytes(dto.getFileIdentification()));
            statement.setBytes(13, convertFileToBytes(dto.getFileDiploma()));

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Të dhënat u ruajtën me sukses.");
                return true;
            } else {
                System.out.println("Nuk u arrit të ruajë të dhënat.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Gabim gjatë ruajtjes së të dhënave: " + e.getMessage());
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Gabim gjatë mbylljes së lidhjes me bazën e të dhënave: " + e.getMessage());
            }
        }
    }

    private static byte[] convertFileToBytes(File file) {
        if (file == null) return null;
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            return bos.toByteArray();
        } catch (IOException e) {
            System.out.println("Gabim gjatë konvertimit të skedarit në bajtë: " + e.getMessage());
            return null;
        }
    }

   public static PersonDTO SearchByPersonalNumber(String personalNumber) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PersonDTO person = null;

        try {

            conn = DBConnector.getConnection();
            String query = "SELECT * FROM tblperson WHERE numriPersonal = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, personalNumber);
            rs = ps.executeQuery();


            if (rs.next()) {
                person = new PersonDTO();
                person.setPersonalNumber(rs.getString("numriPersonal"));
                person.setName(rs.getString("emri"));
                person.setLastName(rs.getString("mbiemri"));
                person.setNationality(rs.getString("nacionaliteti"));
                person.setCity(rs.getString("qyteti"));
                person.setCountry(rs.getString("shteti"));
                person.setGender(rs.getString("gjinia"));
                person.setBirthDate(rs.getDate("dataLindjes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public static void saveStudentAplikant(PersonDTO studentAplikant) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnector.getConnection();
            String query = "INSERT INTO StudentAplikant (numriPersonal, emri, mbiemri, nacionaliteti, qyteti, shteti, gjinia, dataLindjes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, studentAplikant.getPersonalNumber());
            ps.setString(2, studentAplikant.getName());
            ps.setString(3, studentAplikant.getLastName());
            ps.setString(4, studentAplikant.getNationality());
            ps.setString(5, studentAplikant.getCity());
            ps.setString(6, studentAplikant.getCountry());
            ps.setString(7, studentAplikant.getGender());
            ps.setDate(8, studentAplikant.getBirthDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void saveAcademicInterest(AcademicInterestDto dto) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnector.getConnection();

            // marrja e deptid per departamentin dhe prioritetet
            int deptIdPrioritet1 = getDepartmentId(conn, dto.getDept(), getDeptLevel());
            int deptIdPrioritet2 = getDepartmentId(conn, dto.getDept1(), getDeptLevel());
            int deptIdPrioritet3 = getDepartmentId(conn, dto.getDept2(), getDeptLevel());

            if (deptIdPrioritet1 == -1 || deptIdPrioritet2 == -1 || deptIdPrioritet3 == -1) {
                throw new IllegalArgumentException("Invalid department or level");
            }

            
            String query = "INSERT INTO tblAplikimi (userId, deptIdPrioritet1, deptIdPrioritet2, deptIdPrioritet3, afatId) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, getLoggedUser().getId());
            ps.setInt(2, deptIdPrioritet1);
            ps.setInt(3, deptIdPrioritet2);
            ps.setInt(4, deptIdPrioritet3);
            ps.setInt(5, SESSION.getAplicantAfatId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private int getDepartmentId(Connection conn, String departmentName, String level) throws SQLException {
        String query = "SELECT deptId FROM tblDepartamenti WHERE emri = ? AND niveli = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, departmentName);
            ps.setString(2, level);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("deptId");
                }
            }
        }
        return -1; // Kthe -1 nëse departamenti nuk u gjet
    }
}


