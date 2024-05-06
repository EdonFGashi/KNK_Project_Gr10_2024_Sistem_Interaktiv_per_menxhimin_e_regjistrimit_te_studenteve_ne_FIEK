package model.dto.Admin;

public class SaveStudentPersonalInfoDto {
    private String firstName;
    private String lastName;
    private String personalNumber;
    private String personalEmail;
    private String telephone;
    private String nationality;
    private String accepted;
    private String supervisorId;
    public SaveStudentPersonalInfoDto(){

    }
    public SaveStudentPersonalInfoDto(String firstName, String lastName, String personalNumber, String personalEmail, String telephone, String nationality, String accepted, String supervisorId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.personalEmail = personalEmail;
        this.telephone = telephone;
        this.nationality = nationality;
        this.accepted = accepted;
        this.supervisorId = supervisorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }
}
