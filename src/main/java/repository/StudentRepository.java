package repository;

import model.Student;


public class StudentRepository {

    public static Student getByEmail(String Email) {

        String query = "SELECT * FROM tblStudent WHERE email = ? LIMIT 1";
   return null;
    }


}
