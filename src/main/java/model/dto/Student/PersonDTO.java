package model.dto.Student;



import java.sql.Date;

public class PersonDTO {
    private String personalNumber;
    private String name;
    private String lastName;
    private String nationality;
    private String city;
    private String country;
    private String gender;
    private Date birthDate;


    public PersonDTO(String personalNumber, String name, String lastName, String nationality, String city, String country, String gender, Date birthDate) {
        this.personalNumber = personalNumber;
        this.name = name;
        this.lastName = lastName;
        this.nationality = nationality;
        this.city = city;
        this.country = country;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public PersonDTO() {

    }

    // Getters dhe setters
    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}

