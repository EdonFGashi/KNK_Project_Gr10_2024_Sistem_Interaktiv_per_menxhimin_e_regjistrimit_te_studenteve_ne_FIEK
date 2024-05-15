package model.filter;

import java.time.LocalDateTime;

public class StudentFilter implements Filter{
    private String numriPersonal;
    private String email;
    private String emri;
    private String mbiemri;
    private String nacionaliteti;
    private String qyteti;
    private String shteti;
    private String gjinia;
    private String dataLindjes;

    public StudentFilter(String numriPersonal, String email, String emri, String mbiemri, String nacionaliteti, String qyteti, String shteti, String gjinia, String dataLindjes) {
        this.numriPersonal = numriPersonal;
        this.email = email;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.nacionaliteti = nacionaliteti;
        this.qyteti = qyteti;
        this.shteti = shteti;
        this.gjinia = gjinia;
        this.dataLindjes = dataLindjes;
    }

    public String buildQuery(){
        StringBuilder query = new StringBuilder();

        if (!this.emri.isEmpty()) {
            query.append(" AND emri LIKE '" + this.emri + "%'");
        }
        if (!this.mbiemri.isEmpty()) {
            query.append(" AND mbiemri LIKE '" + this.mbiemri + "%'");
        }
        if (!this.email.isEmpty()) {
            query.append(" AND email LIKE '" + this.email + "%'");
        }
        if (!this.nacionaliteti.isEmpty()) {
            query.append(" AND nacionaliteti LIKE '" + this.nacionaliteti + "%'");
        }
        if (!this.gjinia.isEmpty()) {
            query.append(" AND gjinia LIKE '" + this.gjinia + "%'");
        }
        if (!this.shteti.isEmpty()) {
            query.append(" AND shteti LIKE '" + this.shteti + "%'");
        }
        if (!this.qyteti.isEmpty()) {
            query.append(" AND qyteti LIKE '" + this.qyteti + "%'");
        }
        if (!this.numriPersonal.isEmpty()) {
            query.append(" AND numriPersonal LIKE '" + this.numriPersonal + "%'");
        }
        if (!this.dataLindjes.isEmpty()) {
            query.append(" AND dataLindjes >= '" + this.dataLindjes + "'");
        }

        return query.toString();
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
}
