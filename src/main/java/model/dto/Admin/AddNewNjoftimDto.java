package model.dto.Admin;

public class AddNewNjoftimDto {
    private String text;
    private int adminId;

    public AddNewNjoftimDto(String text, int adminId) {
        this.text = text;
        this.adminId = adminId;
    }

    public String getText() {
        return text;
    }

    public int getAdminId() {
        return adminId;
    }
}
