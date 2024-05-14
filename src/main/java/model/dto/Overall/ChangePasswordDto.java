package model.dto.Overall;

public class ChangePasswordDto {

    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
    private String email;

    public ChangePasswordDto( String currentPassword, String newPassword, String confirmPassword, String email) {

        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
        this.email = email;
    }


    public String getCurrentPassword() {
        return currentPassword;
    }
    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getEmail() {
        return this.email;
    }
}
