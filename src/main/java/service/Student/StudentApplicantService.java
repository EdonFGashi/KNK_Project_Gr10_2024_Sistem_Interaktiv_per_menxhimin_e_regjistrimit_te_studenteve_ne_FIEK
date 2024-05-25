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

        StudentApplicantRepository.saveData(dto);
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
            return FXCollections.observableArrayList();
        }
    }

    public static UserStudent2 getUserById(int userId) {
        // Kthe përdoruesin nga tabela tblUserStudent në bazë të ID-së së përdoruesit
        return StudentApplicantRepository.getUserById(userId);
    }

    
}
