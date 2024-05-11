package service.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.RegisteredStudent;
import model.UserStudent;
import model.dto.Admin.RegisteredStudentDetailsToControllerDto;
import repository.StudentRepository;

public class StudentFromAdminService {


    public static ObservableList<UserStudent> searchStudent(String search) {
        try {
            if (search.isEmpty()) {
                return FXCollections.observableArrayList(StudentRepository.getUserStudents());

            } else {
                return FXCollections.observableArrayList(StudentRepository.getUserStudents(search));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }

    }

    public static RegisteredStudentDetailsToControllerDto getRegisteredStudent(int id){

        RegisteredStudent registeredStudent = StudentRepository.getRegisteredStudent(id);

        if(registeredStudent == null){
            return null;
        }else{
            return new RegisteredStudentDetailsToControllerDto(
                    registeredStudent.getGeneratedEmail(), registeredStudent.getGeneratedId(),
                    registeredStudent.getEmriDepartamentit(),registeredStudent.getNiveli()
            );
        }

    }
}
