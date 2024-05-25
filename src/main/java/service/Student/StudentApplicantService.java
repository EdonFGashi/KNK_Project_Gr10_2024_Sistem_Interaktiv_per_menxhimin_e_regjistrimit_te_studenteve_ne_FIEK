package service.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Afat;
import model.UserStudent2;
import model.dto.Student.AcademicInterestDto;
import model.dto.Student.MasterApplicantDto;
import model.dto.Student.PHDApplicantDto;
import repository.AfatRepository;
import repository.StudentApplicant.StudentApplicantRepository;

import model.dto.Student.StudentApplicantDto;


public class StudentApplicantService {

    private static StudentApplicantRepository repository;

    public StudentApplicantService() {
        this.repository = new StudentApplicantRepository();
    }

    
    public static void processAndSaveData(StudentApplicantDto dto)  {
    validateData(dto);
    StudentApplicantRepository.saveData(dto);
}
private static void validateData(StudentApplicantDto dto) {
    if (dto.getMathPoints() < 0 || dto.getMathPoints() > 25 ||
            dto.getAlbanianPoints() < 0 || dto.getAlbanianPoints() > 25 ||
            dto.getEnglishPoints() < 0 || dto.getEnglishPoints() > 25 ||
            dto.getChoosenSubPoints() < 0 || dto.getChoosenSubPoints() > 25 ||
            dto.getSuccesGrade10() < 1.0 || dto.getSuccesGrade10() > 5.0 ||
            dto.getSuccesGrade11() < 1.0 || dto.getSuccesGrade11() > 5.0 ||
            dto.getSuccesGrade12() < 1.0 || dto.getSuccesGrade12() > 5.0) {
        throw new IllegalArgumentException("Invalid data values");
    }
}

    public static boolean processAndSaveMasterData(MasterApplicantDto dto)  {
        // Validate data


        // Save data to repository
        return StudentApplicantRepository.saveMasterData(dto);
    }


    public static boolean processAndSavePHDData(PHDApplicantDto dto)  {
        // Validate data
//        if (dto.getFacultyName() == null  ||
//                dto.getSuccesGradeFirstY() < 6 || dto.getSuccesGradeSecondY() < 6 ||
//                dto.getFileIdentification() == null || dto.getFileBachelor() == null ||
//                dto.getFileMaster() == null || dto.getDeptName().isEmpty()) {
//            throw new IllegalArgumentException("All fields must be completed.");
//        }

        // Save data to repository
        return StudentApplicantRepository.savePHDData(dto);
    }

    public static boolean processAcademicInterest(AcademicInterestDto dto) {
        
        if (dto.getDept() == null || dto.getDept1() == null || dto.getDept2() == null || dto.getDept3() == null) {
            throw new IllegalArgumentException("All departments must be selected.");
        }

        // dergimi i te dhenave per ruajtje ne db
       return StudentApplicantRepository.saveAcademicInterest(dto);
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
            System.out.println(e.getMessage());
            return FXCollections.observableArrayList();
        }
    }

    public static UserStudent2 getUserById(int userId) {
        // Kthe përdoruesin nga tabela tblUserStudent në bazë të ID-së së përdoruesit
        return StudentApplicantRepository.getUserById(userId);
    }

    
}
