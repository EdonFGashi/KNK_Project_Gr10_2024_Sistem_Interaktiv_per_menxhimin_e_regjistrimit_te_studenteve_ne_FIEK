package model;

public class RegisteredStudent extends UserStudent{
    private String generatedEmail;
    private String generatedId;
    private String emriDepartamentit;
    private String niveli;

    public RegisteredStudent(int userId, String numriPersonal, String email, String emri, String mbiemri, String nacionaliteti, String qyteti, String shteti, String gjinia, String dataLindjes, String salt, String passwordHash, String generatedEmail, String registeredEmail, String emriDepartamentit, String niveli) {
        super(userId, numriPersonal, email, emri, mbiemri, nacionaliteti, qyteti, shteti, gjinia, dataLindjes, salt, passwordHash);
        this.generatedEmail = generatedEmail;
        this.generatedId = registeredEmail;
        this.emriDepartamentit = emriDepartamentit;
        this.niveli = niveli;
    }

    public String getGeneratedEmail() {
        return generatedEmail;
    }

    public void setGeneratedEmail(String generatedEmail) {
        this.generatedEmail = generatedEmail;
    }

    public String getRegisteredEmail() {
        return generatedId;
    }

    public String getGeneratedId() {
        return generatedId;
    }

    public String getEmriDepartamentit() {
        return emriDepartamentit;
    }

    public void setEmriDepartamentit(String emriDepartamentit) {
        this.emriDepartamentit = emriDepartamentit;
    }

    public String getNiveli() {
        return niveli;
    }

    public void setNiveli(String niveli) {
        this.niveli = niveli;
    }
}
