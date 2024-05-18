package model.dto.Admin;

public class KonkurimetByAfatDto {
    private int afatId;
    private String niveli;
    private String hera;

    public KonkurimetByAfatDto(int afatId, String niveli, String hera) {
        this.afatId = afatId;
        this.niveli = niveli;
        this.hera = hera;
    }

    public int getAfatId() {
        return afatId;
    }

    public String getNiveli() {
        return niveli;
    }

    public String getHera() {
        return hera;
    }
}
