package model.dto.Overall;



public class UserDto {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    public UserDto(String username, String email, String password, String confirmPassword) {
        this.username=username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
