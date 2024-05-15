package service.Supervisor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Admin;
import model.SupervisorTableModel;
import model.dto.Admin.AdminProfileToControllerDto;
import model.dto.Supervisor.SupervisorCreateInterfaceDto;
import model.dto.Supervisor.SupervisorCreateModelDto;
import model.dto.Supervisor.SupervisorProfileToControllerDto;
import repository.AdminRepository;
import repository.Supervisor.SupervisorRepository;
import service.PasswordHasher;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupervisorService {
    public static boolean signUp(SupervisorCreateInterfaceDto supervisorData) {
        String password = supervisorData.getPassword();
        String confirmPassword = supervisorData.getConfirmPassword();

        if(!password.equals(confirmPassword)){
            return false;
        }


        String salt = PasswordHasher.generateSalt();
        String passwordHash = PasswordHasher.generateSaltedHash(password, salt);

        SupervisorCreateModelDto superVisorNewRecord = new SupervisorCreateModelDto(
                supervisorData.getFirstName(),
                supervisorData.getLastName(),
                supervisorData.getEmail(),
                salt,
                passwordHash
        );

        try {
            return SupervisorRepository.create(superVisorNewRecord);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean supervisorIsFoundByEmail(String email){
        SupervisorTableModel supervisor = SupervisorRepository.getSupervisorByEmail(email);
        if(supervisor == null){
            return false;
        }
        return true;
    }

    public static ObservableList<SupervisorTableModel> searchMbikqyresi(String search) {
        try {
            if (search.isEmpty()) {
                return FXCollections.observableArrayList(SupervisorRepository.getAllMbikqyresiArray());

            } else {
                return FXCollections.observableArrayList(SupervisorRepository.getSupervisorsSearch(search));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    public static SupervisorProfileToControllerDto getProfileInfo(String email) {
        SupervisorTableModel supervisor = SupervisorRepository.getSupervisorByEmail(email);
        if (supervisor == null) {
            return null;
        }
        System.out.println("Admin u murr");
        return new SupervisorProfileToControllerDto(
                supervisor.getFirstName(),
                supervisor.getLastName(),
                supervisor.getEmail()
        );
    }



}
