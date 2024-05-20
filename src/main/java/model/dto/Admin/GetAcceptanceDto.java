package model.dto.Admin;

public class GetAcceptanceDto {
    private String niveli;
    private String hera;

    public GetAcceptanceDto(String niveli, String hera) {
        this.niveli = niveli;
        this.hera = hera;
    }

    public String getNiveli() {
        return niveli;
    }

    public String getHera() {
        return hera;
    }
}
