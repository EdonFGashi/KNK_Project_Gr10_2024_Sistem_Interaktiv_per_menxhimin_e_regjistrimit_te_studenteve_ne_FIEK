package model.dto.Admin;

public class EditRegisteredStudentDetailsOnDbDto {
    private int userId;
    private String generatedEmail;
    private String generatedId;
    private String departmentName;
    private String level;

    public EditRegisteredStudentDetailsOnDbDto(int userId, String generatedEmail, String generatedId,String departmentName, String level) {
        this.userId = userId;
        this.generatedEmail = generatedEmail;
        this.generatedId = generatedId;
        this.level = level;
        this.departmentName = departmentName;
    }

    public int getUserId() {
        return userId;
    }

    public String getGeneratedEmail() {
        return generatedEmail;
    }

    public String getGeneratedId() {
        return generatedId;
    }

    public String getLevel() {
        return level;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
