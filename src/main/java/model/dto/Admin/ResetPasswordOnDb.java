package model.dto.Admin;

public class ResetPasswordOnDb {
    private int id;
    private String salt;
    private String passwordHash;

    public ResetPasswordOnDb(int id, String salt, String passwordHash) {
        this.id = id;
        this.salt = salt;
        this.passwordHash = passwordHash;
    }

    public int getId() {
        return id;
    }

    public String getSalt() {
        return salt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
