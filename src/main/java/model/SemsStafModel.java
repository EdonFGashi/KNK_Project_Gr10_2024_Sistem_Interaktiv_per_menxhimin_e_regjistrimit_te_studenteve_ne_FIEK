package model;

public class SemsStafModel {
    private int stafId;
    private String email;
    private String emri;
    private String mbiemri;
    private String salt;
    private String passwordHash;
    private int facultyId;

    public SemsStafModel(int stafId, String email, String emri, String mbiemri, String salt, String passwordHash, int facultyId) {
        this.stafId = stafId;
        this.email = email;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.salt = salt;
        this.passwordHash = passwordHash;
        this.facultyId = facultyId;
    }

    public int getStafId() {
        return stafId;
    }

    public String getEmail() {
        return email;
    }

    public String getEmri() {
        return emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public String getSalt() {
        return salt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public int getFacultyId() {
        return facultyId;
    }
}
