package model;

import javafx.scene.image.Image;

public class ShkollaMesme {
    private int userId;
    private String emriShkolles;
    private int piketMat;
    private int piketGjSh;
    private int piketAng;
    private int piketZgjedhore;
    private String lendaZgjedhore;
    private int suksesiKl10;
    private int suksesiKl11;
    private int suksesiKl12;
    private Image certifikataNotave;
    private Image leternjoftimi;
    private Image diplomashkolles;
    private boolean approved;

    public ShkollaMesme(int userId, String emriShkolles, int piketMat, int piketGjSh, int piketAng, int piketZgjedhore, String lendaZgjedhore, int suksesiKl10, int suksesiKl11, int suksesiKl12, Image certifikataNotave, Image leternjoftimi, Image diplomashkolles, boolean approved) {
        this.userId=userId;
        this.emriShkolles = emriShkolles;
        this.piketMat = piketMat;
        this.piketGjSh = piketGjSh;
        this.piketAng = piketAng;
        this.piketZgjedhore = piketZgjedhore;
        this.lendaZgjedhore = lendaZgjedhore;
        this.suksesiKl10 = suksesiKl10;
        this.suksesiKl11 = suksesiKl11;
        this.suksesiKl12 = suksesiKl12;
        this.certifikataNotave = certifikataNotave;
        this.leternjoftimi = leternjoftimi;
        this.diplomashkolles = diplomashkolles;
        this.approved = approved;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmriShkolles() {
        return emriShkolles;
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

    public String getLendaZgjedhore() {
        return lendaZgjedhore;
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

    public Image getCertifikataNotave() {
        return certifikataNotave;
    }

    public Image getLeternjoftimi() {
        return leternjoftimi;
    }

    public Image getDiplomashkolles() {
        return diplomashkolles;
    }

    public boolean isApproved() {
        return approved;
    }
}
