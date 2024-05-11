package model.dto.Admin;

public class RegisteredStudentDetailsToControllerDto {
    private String generatedEmail;
    private String generatedId;
    private String DepartmentName;
    private String level;

    public RegisteredStudentDetailsToControllerDto(String generatedEmail, String generatedId, String departmentName, String level) {
        this.generatedEmail = generatedEmail;
        this.generatedId = generatedId;
        DepartmentName = departmentName;
        this.level = level;
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

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
