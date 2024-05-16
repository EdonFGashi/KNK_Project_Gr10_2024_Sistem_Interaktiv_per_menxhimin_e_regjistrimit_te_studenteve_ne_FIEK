package model;

public class SupervisorTableModel {
    private int mbikqyresiId;
    private String firstName;
    private String lastName;
    private String email;
    private String salt;
    private String passwordHash;

    public SupervisorTableModel(int mbikqyresiId, String firstName,String lastName, String email, String salt, String passwordHash) {
        this.mbikqyresiId = mbikqyresiId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salt = salt;
        this.passwordHash = passwordHash;
    }

    public int getMbikqyresiId() {
        return mbikqyresiId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSalt() {
        return salt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
