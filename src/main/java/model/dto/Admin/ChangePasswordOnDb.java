package model.dto.Admin;

public class ChangePasswordOnDb {
    private String email;
    private String newPassword;

    public ChangePasswordOnDb(String email, String newPassword) {
        this.email = email;
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getEmail() {
        return email;
    }
}