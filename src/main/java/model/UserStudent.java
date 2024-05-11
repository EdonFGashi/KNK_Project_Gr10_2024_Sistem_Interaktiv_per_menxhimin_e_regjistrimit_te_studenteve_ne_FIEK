package model;

public class UserStudent {
    protected int userId;
    protected String numriPersonal;
    protected String email;
    protected String emri;
    protected String mbiemri;
    protected String nacionaliteti;
    protected String qyteti;
    protected String shteti;
    protected String gjinia;
    protected String dataLindjes;
    protected String salt;
    protected String passwordHash;
    public UserStudent(int userId, String numriPersonal, String email, String emri, String mbiemri, String nacionaliteti, String qyteti, String shteti, String gjinia, String dataLindjes, String salt, String passwordHash) {
        this.userId = userId;
        this.numriPersonal = numriPersonal;
        this.email = email;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.nacionaliteti = nacionaliteti;
        this.qyteti = qyteti;
        this.shteti = shteti;
        this.gjinia = gjinia;
        this.dataLindjes = dataLindjes;
        this.salt = salt;
        this.passwordHash = passwordHash;
    }

    public int getUserId() {
        return userId;
    }

    public String getNumriPersonal() {
        return numriPersonal;
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

    public String getNacionaliteti() {
        return nacionaliteti;
    }

    public String getQyteti() {
        return qyteti;
    }

    public String getShteti() {
        return shteti;
    }

    public String getGjinia() {
        return gjinia;
    }

    public String getDataLindjes() {
        return dataLindjes;
    }

    public String getSalt() {
        return salt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
