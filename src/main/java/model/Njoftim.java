package model;

public class Njoftim {
    private int njoftimiId;
    private String text;
    private int adminId;

    public Njoftim(int njoftimiId, String text, int adminId) {
        this.njoftimiId = njoftimiId;
        this.text = text;
        this.adminId = adminId;
    }

    public int getNjoftimiId() {
        return njoftimiId;
    }

    public String getText() {
        return text;
    }

    public int getAdminId() {
        return adminId;
    }
}


