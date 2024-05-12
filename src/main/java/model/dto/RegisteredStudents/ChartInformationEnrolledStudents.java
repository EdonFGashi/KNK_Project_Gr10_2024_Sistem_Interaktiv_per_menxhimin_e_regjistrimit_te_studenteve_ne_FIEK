package model.dto.RegisteredStudents;

public class ChartInformationEnrolledStudents {
    private int numri;
    private String departamenti;
    private int viti;

    public ChartInformationEnrolledStudents(int numri, String departamenti) {
        this.numri = numri;
        this.departamenti = departamenti;
    }
    public ChartInformationEnrolledStudents(int viti, int numri) {
        this.numri = numri;
        this.viti = viti;
    }

    public int getNumri() {
        return this.numri;
    }

    public String getDepartamenti() {
        return this.departamenti;
    }

    public int getViti(){
        return this.viti;
    }
}
