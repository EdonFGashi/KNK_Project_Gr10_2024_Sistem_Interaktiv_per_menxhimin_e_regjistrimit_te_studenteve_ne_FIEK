package service.Supervisor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Admin;
import model.SupervisorTableModel;
import model.dto.Admin.AdminProfileToControllerDto;
import model.dto.Admin.ChangePasswordOnDb;
import model.dto.Overall.ChangePasswordDto;
import model.dto.Supervisor.*;
import repository.AdminRepository;
import repository.Supervisor.SupervisorRepository;
import service.CustomExceptions.InvalidPassword;
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

    public static void changePassword(ChangePasswordDto changeData) throws InvalidPassword {
        SupervisorTableModel supervisor = SupervisorRepository.getSupervisorByEmail(changeData.getEmail());

        if(supervisor == null){
            throw new InvalidPassword("Supervisor is not found");
        }

        if(!supervisor.getPasswordHash().equals(PasswordHasher.generateSaltedHash(changeData.getCurrentPassword(),supervisor.getSalt()))){
            throw new InvalidPassword("Invalid Current Password");
        }
        if(changeData.getNewPassword().length() < 8){
            throw new InvalidPassword("Password too short");
        }
        if(!changeData.getNewPassword().equals(changeData.getConfirmPassword())){
            throw new InvalidPassword("New and Confirm do not match");
        }
        if(changeData.getCurrentPassword().equals(changeData.getNewPassword())) {
            throw new InvalidPassword("Cannot be the old Password");
        }

        String saltedHashed = PasswordHasher.generateSaltedHash(changeData.getNewPassword(),supervisor.getSalt());

        if(!SupervisorRepository.changePassword(new ChangePasswordOnDb(supervisor.getEmail(), saltedHashed))){
            throw new InvalidPassword("Database Connection failed");
        };
    }

    public static SupervisorTableModel getSupervisorByEmail(String email){
        return SupervisorRepository.getSupervisorByEmail(email);
    }

//    public static ObservableList<KonkurimetShowDto> searchKonkurimi(String search) {
//        try {
//            if (search.isEmpty()) {
//                return FXCollections.observableArrayList(SupervisorRepository.getAllKonkurimetArray());
//
//            } else {
//                return FXCollections.observableArrayList(SupervisorRepository.getKonkurimetSearch(search));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return FXCollections.observableArrayList();
//        }
//    }

    public static KonkurimetShowDto searchKonkurimi(String search) {
        try {
            if (search.isEmpty()) {
//                return FXCollections.observableArrayList(SupervisorRepository.getAllKonkurimetArray());
                return null;

            } else {
                return (SupervisorRepository.getKonkurimetSearch(search));
            }
        } catch (Exception e) {
            e.printStackTrace();
//            return FXCollections.observableArrayList();
            return null;
        }
    }

    public static ObservableList<KonkurimetShowDto> searchKonkurimet(String search) {
        try {
            if (search.isEmpty()) {
                return FXCollections.observableArrayList(SupervisorRepository.getAllKonkurimetArray());

            } else {
//                return FXCollections.observableArrayList(SupervisorRepository.getSupervisorsSearch(search));
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

}
