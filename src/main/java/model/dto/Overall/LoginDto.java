package model.dto.Overall;

public class LoginDto {

    private String userEmail;
    private String userPassword;

    public LoginDto(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public String getUserEmail() {return userEmail;}
    public String getUserPassword() {return userPassword;}
    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}
    public void setUserPassword(String userPassword) {this.userPassword = userPassword;}

}
