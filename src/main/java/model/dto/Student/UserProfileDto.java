package model.dto.Student;




public class UserProfileDto {
    private String username;
    private String email;

    public UserProfileDto(String username, String email) {
        this.username = username;
        this.email = email;

    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

}
