package model.dto.RegisteredStudents;

public class RegisteredStudents {
    private int userId;
    private String generatedEmail;
    private String registeredEmail;
    private String emriDepartamentit;
    private String niveli;

    public RegisteredStudents(int userId, String generatedEmail, String registeredEmail, String emriDepartamentit, String niveli) {
        this.userId = userId;
        this.generatedEmail = generatedEmail;
        this.registeredEmail = registeredEmail;
        this.emriDepartamentit = emriDepartamentit;
        this.niveli = niveli;
    }

    public RegisteredStudents(int userId,String emriDepartamentit) {
        this.userId = userId;
        this.emriDepartamentit = emriDepartamentit;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getGeneratedEmail() {
        return generatedEmail;
    }

    public String getRegisteredEmail() {
        return registeredEmail;
    }

    public String getEmriDepartamentit() {
        return this.emriDepartamentit;
    }

    public String getNiveli() {
        return this.niveli;
    }

}
