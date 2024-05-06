package model.dto.Admin;

public class SaveRegisteredStudentInfoDto {
    private String departament;
    private String generatedEmail;
    private String generatedId;
    private String yearOfStudy;

    public SaveRegisteredStudentInfoDto(String departament, String generatedEmail, String generatedId, String yearOfStudy) {
        this.departament = departament;
        this.generatedEmail = generatedEmail;
        this.generatedId = generatedId;
        this.yearOfStudy = yearOfStudy;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public String getGeneratedEmail() {
        return generatedEmail;
    }

    public void setGeneratedEmail(String generatedEmail) {
        this.generatedEmail = generatedEmail;
    }

    public String getGeneratedId() {
        return generatedId;
    }

    public void setGeneratedId(String generatedId) {
        this.generatedId = generatedId;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }
}
