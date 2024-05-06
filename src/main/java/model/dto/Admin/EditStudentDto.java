package model.dto.Admin;

public class EditStudentDto {
  private String firstName;
  private String lastName;
  private String personalNumber;
  private String personalEmail;
  private String telephone;
  private String nationality;
  private String accepted;

  private String supervisorId;
  //
  private String departament;
  private String generatedId;
  private String generatedEmail;
  private String yearOfStudy;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getNationality() {
        return nationality;
    }

    public String getAccepted() {
        return accepted;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public String getDepartament() {
        return departament;
    }

    public String getGeneratedId() {
        return generatedId;
    }

    public String getGeneratedEmail() {
        return generatedEmail;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public EditStudentDto(String firstName, String lastName, String personalNumber, String personalEmail, String telephone, String nationality, String accepted, String supervisorId, String departament, String generatedId, String generatedEmail, String yearOfStudy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.personalEmail = personalEmail;
        this.telephone = telephone;
        this.nationality = nationality;
        this.accepted = accepted;
        this.supervisorId = supervisorId;
        this.departament = departament;
        this.generatedId = generatedId;
        this.generatedEmail = generatedEmail;
        this.yearOfStudy = yearOfStudy;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public void setGeneratedId(String generatedId) {
        this.generatedId = generatedId;
    }

    public void setGeneratedEmail(String generatedEmail) {
        this.generatedEmail = generatedEmail;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }
}
