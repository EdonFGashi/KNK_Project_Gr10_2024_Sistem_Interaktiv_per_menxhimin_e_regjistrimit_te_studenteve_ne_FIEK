package model.dto.Student;

import java.io.File;

public class StudentApplicantDto {
    private String SchoolName;
    private int MathPoints;
    private int AlbanianPoints;
    private int EnglishPoints;
    private String ChoosenSub;
    private int ChoosenSubPoints;
    private int totalPoints;
    private double succesGrade10;
    private double succesGrade11;
    private double succesGrade12;
    private File fileCertificate;
    private File fileIdentification;
    private File fileDiploma;

    public StudentApplicantDto(String schoolName, int mathPoints, int albanianPoints, int englishPoints, String choosenSub, int choosenSubPoints, int totalPoints, double succesGrade10, double succesGrade11, double succesGrade12, File fileCertificate, File fileIdentification, File fileDiploma) {
        SchoolName = schoolName;
        MathPoints = mathPoints;
        AlbanianPoints = albanianPoints;
        EnglishPoints = englishPoints;
        ChoosenSub = choosenSub;
        ChoosenSubPoints = choosenSubPoints;
        this.totalPoints = totalPoints;
        this.succesGrade10 = succesGrade10;
        this.succesGrade11 = succesGrade11;
        this.succesGrade12 = succesGrade12;
        this.fileCertificate = fileCertificate;
        this.fileIdentification = fileIdentification;
        this.fileDiploma = fileDiploma;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public File getFileDiploma() {
        return fileDiploma;
    }

    public File getFileIdentification() {
        return fileIdentification;
    }

    public File getFileCertificate() {
        return fileCertificate;
    }

    public double getSuccesGrade12() {
        return succesGrade12;
    }

    public double getSuccesGrade11() {
        return succesGrade11;
    }

    public double getSuccesGrade10() {
        return succesGrade10;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getChoosenSubPoints() {
        return ChoosenSubPoints;
    }

    public String getChoosenSub() {
        return ChoosenSub;
    }

    public int getAlbanianPoints() {
        return AlbanianPoints;
    }

    public int getMathPoints() {
        return MathPoints;
    }

    public int getEnglishPoints() {
        return EnglishPoints;
    }
}
