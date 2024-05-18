package service.Student;




import model.dto.Student.PersonDTO;
import repository.StudentApplicant.StudentApplicantRepository;
import service.DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class personService {


    public personService() {
    }

    // Metoda për të marrë personin nga bazat e të dhënave duke përdorur numrin personal
    public static PersonDTO getPersonByPersonalNumber(String personalNumber) {

  PersonDTO person=  StudentApplicantRepository.SearchByPersonalNumber(personalNumber);

        return person;
    }

    public void saveStudentAplikant(PersonDTO studentAplikant) {
        StudentApplicantRepository.saveStudentAplikant(studentAplikant);
    }
}
