package model.dto.Supervisor;

public class KonkurimetShowDto {
    private int mbikqyresiId;
    private int studentiId;
    private int aplikimiId;
    private int piket;

    public KonkurimetShowDto(int mbikqyresiId, int studentiId, int aplikimiId, int piket) {
        this.mbikqyresiId = mbikqyresiId;
        this.studentiId = studentiId;
        this.aplikimiId = aplikimiId;
        this.piket = piket;
    }

    public int getMbikqyresiId() {return mbikqyresiId;}
    public void setMbikqyresiId(int mbikqyresiId) {this.mbikqyresiId = mbikqyresiId;}

    public int getStudentiId() {return studentiId;}
    public void setStudentiId(int studentiId) {this.studentiId = studentiId;}

    public int getAplikimiId() {return aplikimiId;}
    public void setAplikimiId(int aplikimiId) {this.aplikimiId = aplikimiId;}

    public int getPiket() {return piket;}
    public void setPiket(int piket) {this.piket = piket;}
}
