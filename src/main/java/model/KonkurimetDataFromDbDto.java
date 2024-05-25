package model;

public class KonkurimetDataFromDbDto {
    private int userId;
    private String emri;
    private String mbiemri;
    private int suksesiKl10;
    private int suksesiKl11;
    private int suksesiKl12;
    private int piketMat;
    private int piketGjSh;
    private int piketAng;
    private int piketZgjedhore;
    private String prioriteti1;
    private String prioriteti2;
    private String prioriteti3;
    private String prioriteti4;
    private int afatId;
    private int mbikqyresiId;
    private String niveli;
    private double totalPiket;
    private int piketMatures;
    private double totalPiketMatures;
    private int succes;
    private double totalSucces;
    private int piketPranues;
    private double totalPranues;
    private double total;
    private boolean minoritet;

    public KonkurimetDataFromDbDto(int userId, String emri, String mbiemri, int suksesiKl10, int suksesiKl11, int suksesiKl12, int piketMat, int piketGjSh, int piketAng, int piketZgjedhore, int piketPranues, String prioriteti1, String prioriteti2, String prioriteti3, String prioriteti4, int afatId, int mbikqyresiId, String niveli ,boolean minoritet) {
        this.userId = userId;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.suksesiKl10 = suksesiKl10;
        this.suksesiKl11 = suksesiKl11;
        this.suksesiKl12 = suksesiKl12;
        this.piketMat = piketMat;
        this.piketGjSh = piketGjSh;
        this.piketAng = piketAng;
        this.piketZgjedhore = piketZgjedhore;
        this.piketPranues = piketPranues;
        this.prioriteti1 = prioriteti1;
        this.prioriteti2 = prioriteti2;
        this.prioriteti3 = prioriteti3;
        this.prioriteti4 = prioriteti4;
        this.afatId = afatId;
        this.mbikqyresiId = mbikqyresiId;
        this.niveli = niveli;
        this.minoritet=minoritet;

        if(this.piketMat != 0) {
            //bachelor
            this.piketMatures = this.piketAng + this.piketMat + this.piketGjSh + this.piketZgjedhore;
            this.succes = this.suksesiKl10 + this.suksesiKl11 + this.suksesiKl12;

            this.totalPiketMatures = ((double) this.piketMatures / 100) * 30;
            this.totalSucces = ((double) this.succes / 15) * 30;
            this.totalPranues = ((double) this.piketPranues / 20) * 40;
            this.total = this.totalPiketMatures + this.totalSucces + this.totalPranues;

        }else if(this.piketMat == 0 && this.suksesiKl12 != 0){
            //Master
            this.succes = this.suksesiKl10 + this.suksesiKl11 + this.suksesiKl12;
            this.totalSucces = ((double) this.succes / 30) * 60;
            this.totalPranues = ((double) this.piketPranues / 100) * 40;
            this.total = this.totalSucces + this.totalPranues;
        }else if(this.piketMat == 0 && this.suksesiKl12 == 0){
            //Doktorratur
            this.succes = this.suksesiKl10 + this.suksesiKl11;
            this.totalSucces = ((double) this.succes / 20) * 60;
            this.totalPranues = ((double) this.piketPranues / 100) * 40;
            this.total = this.totalSucces + this.totalPranues;
        }

    }

    public int getUserId() {
        return userId;
    }

    public String getEmri() {
        return emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public int getSuksesiKl10() {
        return suksesiKl10;
    }

    public int getSuksesiKl11() {
        return suksesiKl11;
    }

    public int getSuksesiKl12() {
        return suksesiKl12;
    }

    public int getPiketMat() {
        return piketMat;
    }

    public int getPiketGjSh() {
        return piketGjSh;
    }

    public int getPiketAng() {
        return piketAng;
    }

    public int getPiketZgjedhore() {
        return piketZgjedhore;
    }

    public int getPiketPranues() {
        return piketPranues;
    }

    public String getPrioriteti1() {
        return prioriteti1;
    }

    public String getPrioriteti2() {
        return prioriteti2;
    }

    public String getPrioriteti3() {
        return prioriteti3;
    }

    public String getPrioriteti4() {
        return prioriteti4;
    }

    public int getAfatId() {
        return afatId;
    }

    public int getMbikqyresiId() {
        return mbikqyresiId;
    }

    public String getNiveli() {
        return niveli;
    }


    public String getP1() {
        return this.prioriteti1;
    }
    public String getP2() {
        return this.prioriteti2;
    }
    public String getP3() {
        return this.prioriteti3;
    }
    public String getP4() {
        return this.prioriteti4;
    }

    public double getTotalPiket() {
        return totalPiket;
    }

    public int getPiketMatures() {
        return piketMatures;
    }

    public double getTotalPiketMatures() {
        return totalPiketMatures;
    }

    public int getSucces() {
        return succes;
    }

    public double getTotalSucces() {
        return totalSucces;
    }

    public double getTotalPranues() {
        return totalPranues;
    }

    public double getTotal() {
        return total;
    }

    public boolean isMinoritet() {
        return minoritet;
    }
}