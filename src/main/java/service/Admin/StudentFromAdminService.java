package service.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.RegisteredStudent;
import model.UserStudent;
import model.dto.Admin.EditRegisteredStudentDetailsOnDbDto;
import model.dto.Admin.RegisteredStudentDetailsToControllerDto;
import repository.StudentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

public class StudentFromAdminService {


    public static ObservableList<UserStudent> searchStudents(String search) {
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

    //Advanced Search
    public static ObservableList<UserStudent> searchStudent(String search){

        String [] searchParts = (search.trim().toLowerCase()).split(" ");
        ArrayList<UserStudent> array = StudentRepository.getUserStudents();

        if(search.trim().isEmpty()){
            return FXCollections.observableArrayList(StudentRepository.getUserStudents());
        }

        HashMap<UserStudent, Double> mapimi = new HashMap<>();
        double points;
        if(array!=null) {
            for (UserStudent student : array) {
                HashMap<String, Integer> nameMap = new HashMap<>();
                nameMap.put(Integer.toString(student.getUserId()), 5);
                nameMap.put(student.getEmri().toLowerCase(), 5);
                nameMap.put(student.getNumriPersonal().toLowerCase(),3);
                nameMap.put(student.getEmail().toLowerCase(),2);
                nameMap.put(student.getMbiemri().toLowerCase(),4);
                nameMap.put(student.getNacionaliteti().toLowerCase(),2);
                nameMap.put(student.getQyteti().toLowerCase(),3);
                nameMap.put(student.getShteti().toLowerCase(),1);
                nameMap.put(student.getGjinia().toLowerCase(),1);
                nameMap.put(student.getDataLindjes().toLowerCase(),2);

               points = 0;

                for (String searchPart : searchParts) {
                    for(HashMap.Entry<String, Integer> nameSet : nameMap.entrySet()){
                        if(nameSet.getKey().equals(searchPart)){
                            points+= nameSet.getValue();
                        }else if(nameSet.getKey().contains(searchPart) && searchPart.length()>1){
                            points += (double)nameSet.getValue()/3;
                        }
                    }
                }
                if(points != 0)
                 mapimi.put(student, points);

            }

        }

        ArrayList<UserStudent>finalArray = new ArrayList<>();

        List<Map.Entry<UserStudent,Double>> sortedList =
                mapimi.entrySet().stream().sorted(Map.Entry.<UserStudent,Double>comparingByValue().reversed())
                        .collect(Collectors.toList());

        for(Map.Entry<UserStudent,Double>entry :sortedList){
            finalArray.add(entry.getKey());
        }
//        System.out.println("Startii");
//        for(UserStudent student : finalArray){
//            System.out.println(student.getEmri());
//        }

        return FXCollections.observableArrayList(finalArray);

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

    public static boolean deleteStudent(int userId) {
        return StudentRepository.deleteStudent(userId);
    }

    public static boolean editRegisteredStudent(EditRegisteredStudentDetailsOnDbDto data) {
        return StudentRepository.editRegisteredStudent(data);
    }
}
