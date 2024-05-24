package model.dto.Student;

public class EditUserProfileDto {
    private String username;
    private String email;
    private String oldEmail;

    public EditUserProfileDto(String oldEmail,String username, String email) {
        this.username=username;
        this.email = email;
        this.oldEmail=oldEmail;
    }

    public String getOldEmail() {
        return oldEmail;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
