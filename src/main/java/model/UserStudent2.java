package model;

import java.time.LocalDate;
import java.util.Date;

public class UserStudent2 {
    protected int userId;
    protected String numriPersonal;
    protected String emri;
    protected String mbiemri;
    protected String nacionaliteti;
    protected String qyteti;
    protected String shteti;
    protected String gjinia;
    protected LocalDate dataLindjes;

    public UserStudent2(int userId, String numriPersonal, String emri, String mbiemri, String nacionaliteti, String qyteti, String shteti, String gjinia, LocalDate dataLindjes) {
        this.userId = userId;
        this.numriPersonal = numriPersonal;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.nacionaliteti = nacionaliteti;
        this.qyteti = qyteti;
        this.shteti = shteti;
        this.gjinia = gjinia;
        this.dataLindjes = dataLindjes;

    }

    public int getUserId() {
        return userId;
    }

    public String getNumriPersonal() {
        return numriPersonal;
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

    public LocalDate getDataLindjes() {return this.dataLindjes;}

}
