package model.dto.Admin;

public class DepartmentAcceptanceDto {
    private int IKSNormal;
    private int IKSMinoritet;
    private int EARNormal;
    private int EARMinoritet;
    private int EENormal;
    private int EEMinoritet;
    private int TIKNormal;
    private int TIKMinoritet;

    public DepartmentAcceptanceDto(int IKSNormal, int IKSMinoritet, int EARNormal, int EARMinoritet, int EENormal, int EEMinoritet, int TIKNormal, int TIKMinoritet) {
        this.IKSNormal = IKSNormal;
        this.IKSMinoritet = IKSMinoritet;
        this.EARNormal = EARNormal;
        this.EARMinoritet = EARMinoritet;
        this.EENormal = EENormal;
        this.EEMinoritet = EEMinoritet;
        this.TIKNormal = TIKNormal;
        this.TIKMinoritet = TIKMinoritet;
    }

    public int getIKSNormal() {
        return IKSNormal;
    }

    public int getIKSMinoritet() {
        return IKSMinoritet;
    }

    public int getEARNormal() {
        return EARNormal;
    }

    public int getEARMinoritet() {
        return EARMinoritet;
    }

    public int getEENormal() {
        return EENormal;
    }

    public int getEEMinoritet() {
        return EEMinoritet;
    }

    public int getTIKNormal() {
        return TIKNormal;
    }

    public int getTIKMinoritet() {
        return TIKMinoritet;
    }
}