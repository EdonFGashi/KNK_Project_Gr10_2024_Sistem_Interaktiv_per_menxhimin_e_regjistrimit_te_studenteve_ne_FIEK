package model.dto.Admin;

import model.KonkurimetDataFromDbDto;

import java.util.ArrayList;

public class RegistrationListsToController {
    private ArrayList<KonkurimetDataFromDbDto> iksNormal;
    private ArrayList<KonkurimetDataFromDbDto> earNormal;
    private ArrayList<KonkurimetDataFromDbDto> eeNormal;
    private ArrayList<KonkurimetDataFromDbDto> tikNormal;
    private ArrayList<KonkurimetDataFromDbDto> paPranuarNormal;

    private ArrayList<KonkurimetDataFromDbDto> iksMinoritet;
    private ArrayList<KonkurimetDataFromDbDto> earMinoritet;
    private ArrayList<KonkurimetDataFromDbDto> eeMinoritet;
    private ArrayList<KonkurimetDataFromDbDto> tikMinoritet;
    private ArrayList<KonkurimetDataFromDbDto> paPranuarMinoritet;

    public RegistrationListsToController(ArrayList<KonkurimetDataFromDbDto> iksNormal, ArrayList<KonkurimetDataFromDbDto> earNormal, ArrayList<KonkurimetDataFromDbDto> eeNormal, ArrayList<KonkurimetDataFromDbDto> tikNormal, ArrayList<KonkurimetDataFromDbDto> paPranuarNormal, ArrayList<KonkurimetDataFromDbDto> iksMinoritet, ArrayList<KonkurimetDataFromDbDto> earMinoritet, ArrayList<KonkurimetDataFromDbDto> eeMinoritet, ArrayList<KonkurimetDataFromDbDto> tikMinoritet, ArrayList<KonkurimetDataFromDbDto> paPranuarMinoritet) {
        this.iksNormal = iksNormal;
        this.earNormal = earNormal;
        this.eeNormal = eeNormal;
        this.tikNormal = tikNormal;
        this.paPranuarNormal = paPranuarNormal;
        this.iksMinoritet = iksMinoritet;
        this.earMinoritet = earMinoritet;
        this.eeMinoritet = eeMinoritet;
        this.tikMinoritet = tikMinoritet;
        this.paPranuarMinoritet = paPranuarMinoritet;
    }

    public ArrayList<KonkurimetDataFromDbDto> getIksNormal() {
        return iksNormal;
    }

    public ArrayList<KonkurimetDataFromDbDto> getEarNormal() {
        return earNormal;
    }

    public ArrayList<KonkurimetDataFromDbDto> getEeNormal() {
        return eeNormal;
    }

    public ArrayList<KonkurimetDataFromDbDto> getTikNormal() {
        return tikNormal;
    }

    public ArrayList<KonkurimetDataFromDbDto> getPaPranuarNormal() {
        return paPranuarNormal;
    }

    public ArrayList<KonkurimetDataFromDbDto> getIksMinoritet() {
        return iksMinoritet;
    }

    public ArrayList<KonkurimetDataFromDbDto> getEarMinoritet() {
        return earMinoritet;
    }

    public ArrayList<KonkurimetDataFromDbDto> getEeMinoritet() {
        return eeMinoritet;
    }

    public ArrayList<KonkurimetDataFromDbDto> getTikMinoritet() {
        return tikMinoritet;
    }

    public ArrayList<KonkurimetDataFromDbDto> getPaPranuarMinoritet() {
        return paPranuarMinoritet;
    }
}
