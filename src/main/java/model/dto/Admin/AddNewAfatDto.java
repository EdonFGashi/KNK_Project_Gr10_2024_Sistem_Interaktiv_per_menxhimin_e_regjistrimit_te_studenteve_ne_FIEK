package model.dto.Admin;

import java.util.Date;

public class AddNewAfatDto {
    private int year;
    private String hera;
    private String dataHapjes;
    private String dataMbylljes;
    private String niveli;

    public AddNewAfatDto(int year, String hera, String dataHapjes, String dataMbylljes, String niveli) {
        this.year = year;
        this.hera = hera;
        this.dataHapjes = dataHapjes;
        this.dataMbylljes = dataMbylljes;
        this.niveli = niveli;
    }

    public int getYear() {
        return year;
    }

    public String getHera() {
        return hera;
    }

    public String getDataHapjes() {
        return dataHapjes;
    }

    public String getDataMbylljes() {
        return dataMbylljes;
    }

    public String getNiveli() {
        return niveli;
    }
}
