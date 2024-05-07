package model.dto.Admin;

public class LoginAdminDto {
    private String email;
    private String password;

    public LoginAdminDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
