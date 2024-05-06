package service.Admin;

import model.RegisteredStudent;
import model.Student;
import model.dto.Admin.EditStudentDto;
import model.dto.Admin.SaveRegisteredStudentInfoDto;
import model.dto.Admin.SaveStudentPersonalInfoDto;
import repository.RegisteredStudentRepository;
import repository.StudentRepository;
import service.PasswordHasher;

public class StudentFromAdminService {


    public static EditStudentDto searchStudentEmail(String email){

         Student student = StudentRepository.getByEmail(email);
         if(student == null){
             return null;
         }
         if(StudentRepository.isRegistered(email)){
             RegisteredStudent registeredStudent = StudentRepository.getRegisteredByEmail(email);
         }

        // EditStudentDto editStudentDto = new();


        return null;
    }

    public static boolean saveStudentPersonalInfo(SaveStudentPersonalInfoDto saveStudentPersonalInfoDto){
        Student student = new Student();
        //

       return StudentRepository.saveStudent(student);

    }
    public static boolean saveRegisteredStudent(SaveRegisteredStudentInfoDto saveRegisteredStudentInfoDto){
       RegisteredStudent registeredStudent = new RegisteredStudent();
        //

        return RegisteredStudentRepository.saveRegisteredStudent(registeredStudent);
    }


}
