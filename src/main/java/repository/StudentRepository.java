package repository;

import model.RegisteredStudent;
import model.Student;


public class StudentRepository {

    public static Student getByEmail(String Email) {

        String query = "SELECT * FROM tblStudent WHERE email = ? LIMIT 1";
   return null;
    }

    public static boolean isRegistered(String Email){
        String query = "SELECT * FROM tblStudent JOIN....";
        return false;
    }
    public static RegisteredStudent getRegisteredByEmail(String email){
        String query = "SELECT * FROM tblRegisteredStudents WHERE email = ? OR email_adress = ? LIMIT 1";
        return null;
    }
    public static boolean saveStudent(Student student){


        return false;
    }

}
