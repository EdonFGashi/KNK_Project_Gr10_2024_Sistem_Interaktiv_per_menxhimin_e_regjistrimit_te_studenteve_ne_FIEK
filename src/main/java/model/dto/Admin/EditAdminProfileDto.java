package model.dto.Admin;

public class EditAdminProfileDto {
    private String firstname;
    private String lastName;
    private String email;
    private String oldEmail;

    public EditAdminProfileDto(String oldEmail,String firstname, String lastName, String email) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.email = email;
        this.oldEmail=oldEmail;
    }

    public String getOldEmail() {
        return oldEmail;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
