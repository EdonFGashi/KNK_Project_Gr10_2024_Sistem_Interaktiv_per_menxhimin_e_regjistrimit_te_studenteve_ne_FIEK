package service.Student;
import repository.StudentApplicant.StudentApplicantRepository;

import model.dto.Student.StudentApplicantDto;


public class StudentApplicantService {

    private final StudentApplicantRepository repository;

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
}
