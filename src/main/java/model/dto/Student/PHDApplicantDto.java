package model.dto.Student;

import java.io.File;

public class PHDApplicantDto {
    private int userId;
    private String FacultyName;
    private double succesGradeFirstY;
    private double succesGradeSecondY;
    private File fileBachelor;
    private File fileIdentification;
    private File fileMaster;

    public PHDApplicantDto(int id, String FacultyName, double succesGrade1, double succesGrade2, File fileBchelor, File fileIdentification, File fileMaster) {
        this.userId=id;
        this.FacultyName= FacultyName;
        this.succesGradeFirstY = succesGrade1;
        this.succesGradeSecondY = succesGrade2;
        this.fileBachelor = fileBchelor;
        this.fileIdentification = fileIdentification;
        this.fileMaster = fileMaster;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFacultyName() {
        return FacultyName;
    }

    public void setFacultyName(String facultyName) {
        FacultyName = facultyName;
    }

    public double getSuccesGradeFirstY() {
        return succesGradeFirstY;
    }

    public void setSuccesGradeFirstY(double succesGradeFirstY) {
        this.succesGradeFirstY = succesGradeFirstY;
    }

    public double getSuccesGradeSecondY() {
        return succesGradeSecondY;
    }

    public void setSuccesGradeSecondY(double succesGradeSecondY) {
        this.succesGradeSecondY = succesGradeSecondY;
    }

    public File getFileBachelor() {
        return fileBachelor;
    }

    public void setFileBachelor(File fileBachelor) {
        this.fileBachelor = fileBachelor;
    }

    public File getFileIdentification() {
        return fileIdentification;
    }

    public void setFileIdentification(File fileIdentification) {
        this.fileIdentification = fileIdentification;
    }

    public File getFileMaster() {
        return fileMaster;
    }

    public void setFileMaster(File fileMaster) {
        this.fileMaster = fileMaster;
    }
}
