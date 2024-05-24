package model;

import java.time.LocalDate;
import java.util.Date;

public class Arkiva {
    private int documentId;
    private String nrSerik;
    private String idStudentit;
    private LocalDate data;

    public Arkiva(int documentId, String nrSerik, String idStudentit, LocalDate data) {
        this.documentId = documentId;
        this.nrSerik = nrSerik;
        this.idStudentit = idStudentit;
        this.data = data;
    }
    public Arkiva(String nrSerik, String idStudentit, LocalDate data) {
        this.nrSerik = nrSerik;
        this.idStudentit = idStudentit;
        this.data = data;
    }

    public int getDocumentId() {
        return documentId;
    }

    public String getNrSerik() {
        return nrSerik;
    }

    public String getIdStudentit() {
        return idStudentit;
    }
    public LocalDate getData() {
        return  data;
    }
}
