package repository.StudentApplicant;

import controller.SESSION;
import model.dto.Student.*;
import service.DBConnector;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static controller.SESSION.*;

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



        public static boolean saveMasterData(MasterApplicantDto dto) {
            Connection conn = DBConnector.getConnection();
            if (conn == null) {
                System.out.println("Lidhja me bazën e të dhënave nuk është e mundur.");
                return false;
            }

            // Query për të ruajtur të dhënat në tabelën tblShkollaMesme
            String queryShkollaMesme = "INSERT INTO tblShkollaMesme (userId, emriShkolles, suksesiKl10, suksesiKl11, suksesiKl12, leternjoftimi, diplomaShkolles) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statementShkollaMesme = conn.prepareStatement(queryShkollaMesme)) {
                statementShkollaMesme.setInt(1, dto.getUserId());
                statementShkollaMesme.setString(2, dto.getFacultyName());
                statementShkollaMesme.setDouble(3, dto.getFirstYear());
                statementShkollaMesme.setDouble(4, dto.getSecondYear());
                statementShkollaMesme.setDouble(5, dto.getThirdYear());
                statementShkollaMesme.setBytes(6, convertFileToBytes(dto.getFileIdentification()));
                statementShkollaMesme.setBytes(7, convertFileToBytes(dto.getFileBachelorDegree()));

                int affectedRowsShkollaMesme = statementShkollaMesme.executeUpdate();
                if (affectedRowsShkollaMesme > 0) {
                    System.out.println("Të dhënat në tblShkollaMesme u ruajtën me sukses.");
                } else {
                    System.out.println("Nuk u arrit të ruajë të dhënat në tblShkollaMesme.");
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Gabim gjatë ruajtjes së të dhënave në tblShkollaMesme: " + e.getMessage());
                return false;
            }


            // Query për të ruajtur të dhënat në tabelën tblAplikimi
            String queryAplikimi = "INSERT INTO tblAplikimi (shkollaId, deptIdPrioritet1,afatId) VALUES (?, ?, ?, ?)";

            try (PreparedStatement statementAplikimi = conn.prepareStatement(queryAplikimi)) {
                int deptId=getDepartmentId(conn, dto.getDeptName(),getDeptLevel());

                statementAplikimi.setInt(1, findShkollaIdByUserId(dto.getUserId(),conn));
                statementAplikimi.setInt(2, deptId);
                statementAplikimi.setNull(3, SESSION.getAplicantAfatId());


                int affectedRowsAplikimi = statementAplikimi.executeUpdate();
                if (affectedRowsAplikimi > 0) {
                    System.out.println("Të dhënat në tblAplikimi u ruajtën me sukses.");
                    return true;
                } else {
                    System.out.println("Nuk u arrit të ruajë të dhënat në tblAplikimi.");
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Gabim gjatë ruajtjes së të dhënave në tblAplikimi: " + e.getMessage());
                return false;
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Gabim gjatë mbylljes së lidhjes me bazën e të dhënave: " + e.getMessage());
                }
            }
        }


    public static boolean savePHDData(PHDApplicantDto dto) {
        Connection conn = DBConnector.getConnection();
        if (conn == null) {
            System.out.println("Lidhja me bazën e të dhënave nuk është e mundur.");
            return false;
        }

        // Query për të ruajtur të dhënat në tabelën tblShkollaMesme
        String queryShkollaMesme = "INSERT INTO tblShkollaMesme (userId, emriShkolles, suksesiKl10, suksesiKl11, certifikataNotave, leternjoftimi, diplomaShkolles) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statementShkollaMesme = conn.prepareStatement(queryShkollaMesme)) {
            statementShkollaMesme.setInt(1, dto.getUserId());
            statementShkollaMesme.setString(2, dto.getFacultyName());
            statementShkollaMesme.setDouble(3, dto.getSuccesGradeFirstY());
            statementShkollaMesme.setDouble(4, dto.getSuccesGradeSecondY());
            statementShkollaMesme.setBytes(5,  convertFileToBytes(dto.getFileBachelor()));
            statementShkollaMesme.setBytes(6, convertFileToBytes(dto.getFileIdentification()));
            statementShkollaMesme.setBytes(7, convertFileToBytes(dto.getFileMaster()));

            int affectedRowsShkollaMesme = statementShkollaMesme.executeUpdate();
            if (affectedRowsShkollaMesme > 0) {
                System.out.println("Të dhënat në tblShkollaMesme u ruajtën me sukses.");
            } else {
                System.out.println("Nuk u arrit të ruajë të dhënat në tblShkollaMesme.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Gabim gjatë ruajtjes së të dhënave në tblShkollaMesme: " + e.getMessage());
            return false;
        }


        // Query për të ruajtur të dhënat në tabelën tblAplikimi
        String queryAplikimi = "INSERT INTO tblAplikimi (shkollaId, deptIdPrioritet1,afatId) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statementAplikimi = conn.prepareStatement(queryAplikimi)) {
            int deptId=getDepartmentId(conn, dto.getDeptName(),getDeptLevel());

            statementAplikimi.setInt(1, findShkollaIdByUserId(dto.getUserId(),conn));
            statementAplikimi.setInt(2, deptId);
            statementAplikimi.setNull(3, SESSION.getAplicantAfatId());


            int affectedRowsAplikimi = statementAplikimi.executeUpdate();
            if (affectedRowsAplikimi > 0) {
                System.out.println("Të dhënat në tblAplikimi u ruajtën me sukses.");
                return true;
            } else {
                System.out.println("Nuk u arrit të ruajë të dhënat në tblAplikimi.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Gabim gjatë ruajtjes së të dhënave në tblAplikimi: " + e.getMessage());
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Gabim gjatë mbylljes së lidhjes me bazën e të dhënave: " + e.getMessage());
            }
        }
    }


    private static int findShkollaIdByUserId(int userId, Connection conn) {
        String query = "SELECT shkollaId FROM tblShkollaMesme WHERE userId = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("shkollaId");
                }
            }
        } catch (SQLException e) {
            System.out.println("Gabim gjatë kërkimit të shkollaId: " + e.getMessage());
        }
        return -1; // Kthe -1 nëse nuk gjendet shkollaId për këtë userId
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
            int deptId = getDepartmentId(conn, dto.getDept(), getDeptLevel());
            int deptIdPrioritet1 = getDepartmentId(conn, dto.getDept1(), getDeptLevel());
            int deptIdPrioritet2 = getDepartmentId(conn, dto.getDept2(), getDeptLevel());
            int deptIdPrioritet3 = getDepartmentId(conn, dto.getDept3(), getDeptLevel());

            if (deptIdPrioritet1 == -1 || deptIdPrioritet2 == -1 || deptIdPrioritet3 == -1) {
                throw new IllegalArgumentException("Invalid department or level");
            }

            
            String query = "INSERT INTO tblAplikimi (userId, deptIdPrioritet1, deptIdPrioritet2, deptIdPrioritet3,deptIdPrioritet4, afatId) VALUES (?, ?, ?, ?, ?,?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, getLoggedUser().getId());
            ps.setInt(2, deptId);
            ps.setInt(3, deptIdPrioritet1);
            ps.setInt(4, deptIdPrioritet2);
            ps.setInt(5, deptIdPrioritet3);
            ps.setInt(6, SESSION.getAplicantAfatId());

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

    private static int getDepartmentId(Connection conn, String departmentName, String level) throws SQLException {
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


