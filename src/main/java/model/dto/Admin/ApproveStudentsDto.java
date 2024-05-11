package model.dto.Admin;

public class ApproveStudentsDto {
    private boolean aprove;
    private int id;

    public ApproveStudentsDto(boolean aprove, int id) {
        this.aprove = aprove;
        this.id = id;
    }

    public boolean isAprove() {
        return aprove;
    }

    public int getId() {
        return id;
    }
}
