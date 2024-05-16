package service.Admin;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Admin;
import model.Afat;
import model.Njoftim;
import model.dto.Admin.*;

import model.dto.Overall.ChangePasswordDto;
import model.dto.ResetPasswordOnDb;
import model.filter.NjoftimPagination;
import repository.AdminRepository;
import repository.AfatRepository;
import repository.NjoftimRepository;
import repository.Supervisor.SupervisorRepository;
import service.CustomExceptions.InvalidPassword;
import service.PasswordHasher;
import controller.SESSION;
import repository.StudentRepository;

import java.util.ArrayList;

public class AdminService {
public static boolean login(LoginAdminDto loginData){

    Admin admin = AdminRepository.getByEmail(loginData.getEmail());
    if(admin==null){
        System.out.println("Admini nuk u murr");
        return false;

    }

    System.out.println("Admini u murr");

    String password = loginData.getPassword();
    String salt = admin.getSalt();
    String passwordHash = admin.getHashedPassword();

    //veq krahason hash me salt a eshte i njejte
    return PasswordHasher.compareSaltedHash(
            password,salt,passwordHash
    );
  }


    public static void changePassword(ChangePasswordDto changeData) throws InvalidPassword{
    Admin admin = AdminRepository.getByEmail(changeData.getEmail());

    if(admin == null){
        throw new InvalidPassword("Admin is not found");
    }

    if(!admin.getHashedPassword().equals(PasswordHasher.generateSaltedHash(changeData.getCurrentPassword(),admin.getSalt()))){
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

    String saltedHashed = PasswordHasher.generateSaltedHash(changeData.getNewPassword(),admin.getSalt());

    if(!AdminRepository.changePassword(new ChangePasswordOnDb(admin.getEmail(), saltedHashed))){
        throw new InvalidPassword("Database Connection failed");
    };
    }
    public static boolean editAfat(Afat afat) {
        return AfatRepository.editAfat(afat);
    }

    public static AdminProfileToControllerDto getProfileInfo(String email) {
        Admin admin = AdminRepository.getByEmail(email);
        if (admin == null) {
            return null;
        }
        System.out.println("Admin u murr");
        return new AdminProfileToControllerDto(
                admin.getFirstName(),
                admin.getLastName(),
                admin.getEmail()
        );
    }
    public static boolean addNewAfat(AddNewAfatDto afat) {
        return AfatRepository.addNewAfat(afat);
    }

    public static ObservableList<Afat> searchAfat(String search){
        try {
            if (search.isEmpty()) {
                return FXCollections.observableArrayList(AfatRepository.getAllAfatArray());
            } else {
                return FXCollections.observableArrayList(AfatRepository.getAfatArraySearch(search));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }


    public static void resetPassword(ResetPasswordDto resetData) throws InvalidPassword{
        if(resetData.getNewPassword().length() < 8){
            throw new InvalidPassword("Password too short");
        }

        if(!resetData.getNewPassword().equals(resetData.getConfirmPassword())){
            throw new InvalidPassword("New and Confirm do not match!");
        }

       String salt = PasswordHasher.generateSalt();
       int id = SESSION.getAdmin_reset_PasswordId();
       String passwordHash = PasswordHasher.generateSaltedHash(resetData.getNewPassword(),salt);

        ResetPasswordOnDb resetPasswordOnDb = new ResetPasswordOnDb(
          id,salt,passwordHash
        );

       if(SESSION.getAdmin_reset_type().equals("Supervisor")){
           if(!SupervisorRepository.resetPassword(resetPasswordOnDb)){
               throw new InvalidPassword("Problem in Database!");
           }
       }else if(SESSION.getAdmin_reset_type().equals("Student")){
           if(!StudentRepository.resetPassword(resetPasswordOnDb)){
               throw new InvalidPassword("Problem in Database!");
           }
       }

    }



    public static boolean savePersonalDetails(EditAdminProfileDto editData) {
    return AdminRepository.savePersonalDetails(editData);
    }


    public static boolean addNewNjoftim(AddNewNjoftimDto data){
       return  NjoftimRepository.addNewNjoftim(data);
    }

    public static Admin getAdminByEmail(String email){
    return AdminRepository.getByEmail(email);
    }

    public static ArrayList<Njoftim> getAllNjoftimet() {
    return NjoftimRepository.getAllNjoftimArray();
    }

    public static ArrayList<Njoftim> getNjoftimiWithPagination(NjoftimPagination pagination) {
    return NjoftimRepository.getPagenatedNjoftim(pagination);
    }

    public static int getTotalNjoftime() {
    return NjoftimRepository.getTotalNjoftimi();
    }
}
