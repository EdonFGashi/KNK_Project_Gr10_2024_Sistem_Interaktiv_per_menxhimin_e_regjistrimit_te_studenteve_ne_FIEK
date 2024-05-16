package model.dto.Admin;

public class ResetPasswordDto {
    private String newPassword;
    private String confirmPassword;
    private int id;
    private String type;

    public ResetPasswordDto(String newPassword, String confirmPassword, int id, String type) {
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
        this.id = id;
        this.type = type;
    }


    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
