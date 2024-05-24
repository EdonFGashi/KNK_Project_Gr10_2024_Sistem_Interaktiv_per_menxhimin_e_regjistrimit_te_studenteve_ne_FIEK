package model.dto.Student;
import java.io.File;

public class MasterApplicantDto {
    private int userId;
    private String FacultyName;
    private double firstYear;
    private double secondYear;
    private double thirdYear;
    private File fileIdentification;
    private File fileBachelorDegree;
    private String deptName;

    public MasterApplicantDto(int userId, String facultyName, double firstYear, double secondYear, double thirdYear, File fileIdentification, File fileBachelorDegree,String deptName) {
        this.userId = userId;
        FacultyName = facultyName;
        this.firstYear = firstYear;
        this.secondYear = secondYear;
        this.thirdYear = thirdYear;
        this.fileIdentification = fileIdentification;
        this.fileBachelorDegree = fileBachelorDegree;
        this.deptName=deptName;
    }

    public int getUserId() {
        return userId;
    }

    public String getFacultyName() {
        return FacultyName;
    }

    public double getFirstYear() {
        return firstYear;
    }

    public double getSecondYear() {
        return secondYear;
    }

    public double getThirdYear() {
        return thirdYear;
    }

    public File getFileIdentification() {
        return fileIdentification;
    }

    public File getFileBachelorDegree() {
        return fileBachelorDegree;
    }

    public String getDeptName() {
        return deptName;
    }
}
