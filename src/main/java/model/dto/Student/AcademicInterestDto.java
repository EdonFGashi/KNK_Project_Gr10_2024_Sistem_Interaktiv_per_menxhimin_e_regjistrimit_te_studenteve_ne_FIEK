package model.dto.Student;


public class AcademicInterestDto {

    private String dept;
    private String dept1;
    private String dept2;
    private String dept3;

    public AcademicInterestDto(String dept, String dept1, String dept2, String dept3) {
        this.dept = dept;
        this.dept1 = dept1;
        this.dept2 = dept2;
        this.dept3 = dept3;
    }

    // Getters and setters

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDept1() {
        return dept1;
    }

    public void setDept1(String dept1) {
        this.dept1 = dept1;
    }

    public String getDept2() {
        return dept2;
    }

    public void setDept2(String dept2) {
        this.dept2 = dept2;
    }

    public String getDept3() {
        return dept3;
    }

    public void setDept3(String dept3) {
        this.dept3 = dept3;
    }
}
