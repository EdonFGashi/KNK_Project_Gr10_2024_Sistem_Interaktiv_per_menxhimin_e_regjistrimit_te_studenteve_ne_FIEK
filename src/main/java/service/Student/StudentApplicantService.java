package service.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Afat;
import model.dto.Student.AcademicInterestDto;
import repository.AfatRepository;
import repository.StudentApplicant.StudentApplicantRepository;

import model.dto.Student.StudentApplicantDto;


public class StudentApplicantService {

    private static StudentApplicantRepository repository;

    public StudentApplicantService() {
        this.repository = new StudentApplicantRepository();
    }

    public static void processAndSaveData(StudentApplicantDto dto) throws Exception {
        // Validimi i të dhënave
        if (dto.getSchoolName() == null || dto.getMathPoints()<0 ||
                dto.getAlbanianPoints() <0 || dto.getChoosenSub().isEmpty() ||
                dto.getChoosenSubPoints() <0 || dto. getTotalPoints() <0|| dto. getEnglishPoints() <0|| dto. getSuccesGrade10() <0 ||dto. getSuccesGrade11() <0 ||dto. getSuccesGrade12() <0 || dto.getFileCertificate() == null
                || dto.getFileIdentification() == null|| dto.getFileDiploma() == null) {
            throw new IllegalArgumentException("Të gjitha fushat duhet të plotësohen.");
        }

       
        StudentApplicantRepository.saveData(dto);
    }

    public static void processAcademicInterest(AcademicInterestDto dto) {
        
        if (dto.getDept() == null || dto.getDept1() == null || dto.getDept2() == null || dto.getDept3() == null) {
            throw new IllegalArgumentException("All departments must be selected.");
        }

        // dergimi i te dhenave per ruajtje ne db
        repository.saveAcademicInterest(dto);
    }

    public static ObservableList<Afat> searchAfatByLevel(String Level){
        try {
            if (Level.isEmpty()) {
                return FXCollections.observableArrayList(AfatRepository.getAllAfatArray());
            } else {
                return FXCollections.observableArrayList(AfatRepository.getAfatArrayByDateAndLevel(Level));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    
}
