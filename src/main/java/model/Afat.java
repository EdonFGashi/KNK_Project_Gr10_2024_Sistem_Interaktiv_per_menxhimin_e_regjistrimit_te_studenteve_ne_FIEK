package model;

import java.util.Date;

public class Afat {
    private int id;
    private String hera;
    private String dataHapjes;
    private String dataMbylljes;
    private String niveli;

    public Afat(int id, String hera, String dataHapjes, String dataMbylljes, String niveli) {
        this.id = id;
        this.hera = hera;
        this.dataHapjes = dataHapjes;
        this.dataMbylljes = dataMbylljes;
        this.niveli = niveli;
    }

    public int getId() {
        return id;
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