package model.dto.Supervisor;

public class AplikimetDto {
    private int aplikimiId;
    private int shkollaId;
    private int deptIdPrioritet1;
    private int deptIdPrioritet2;
    private int deptIdPriotitet3;
    private int deptIdPrioritet4;
    private int afatId;

    public AplikimetDto(int aplikimiId,
                        int shkollaId,
                        int deptIdPrioritet1,
                        int deptIdPrioritet2,
                        int deptIdPriotitet3,
                        int deptIdPrioritet4,
                        int afatId) {
        this.aplikimiId = aplikimiId;
        this.shkollaId = shkollaId;
        this.deptIdPrioritet1 = deptIdPrioritet1;
        this.deptIdPrioritet2 = deptIdPrioritet2;
        this.deptIdPriotitet3 = deptIdPriotitet3;
        this.deptIdPrioritet4 = deptIdPrioritet4;
        this.afatId = afatId;
    }

    public int getAplikimiId() {
        return aplikimiId;
    }
}
