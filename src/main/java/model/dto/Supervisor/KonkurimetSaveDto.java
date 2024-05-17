package model.dto.Supervisor;

public class KonkurimetSaveDto {
    private int aplikimiId;
    private int piket;
    private int supervisorId;

    public KonkurimetSaveDto(int aplikimiId, int piket, int supervisorId) {
        this.aplikimiId = aplikimiId;
        this.piket = piket;
        this.supervisorId = supervisorId;
    }

    public int getAplikimiId() {return aplikimiId;}
    public void setAplikimiId(int aplikimiId) {this.aplikimiId = aplikimiId;}

    public int getPiket() {return piket;}
    public void setPiket(int piket) {this.piket = piket;}

    public int getSupervisorId() {return supervisorId;}
    public void setSupervisorId(int supervisorId) {this.supervisorId = supervisorId;}
}
