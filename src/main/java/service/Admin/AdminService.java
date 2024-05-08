package service.Admin;


import model.Admin;
import model.dto.Admin.AdminProfileToControllerDto;
import model.dto.Admin.ChangePasswordOnDb;
import model.dto.Admin.EditAdminProfileDto;
import model.dto.Admin.LoginAdminDto;
import model.dto.Overall.ChangePasswordDto;
import repository.AdminRepository;
import service.CustomExceptions.InvalidPassword;
import service.PasswordHasher;

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
        throw new InvalidPassword("  Admin is not found");
    }

    if(!admin.getHashedPassword().equals(PasswordHasher.generateSaltedHash(changeData.getCurrentPassword(),admin.getSalt()))){
        throw new InvalidPassword("  Invalid Current Password");
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


    public static AdminProfileToControllerDto getProfileInfo(String email) {
        Admin admin = AdminRepository.getByEmail(email);
        if (admin == null) {
            System.out.println("Admin nuk u murr");
            return null;
        }
        System.out.println("Admin u murr");
        return new AdminProfileToControllerDto(
                admin.getFirstName(),
                admin.getLastName(),
                admin.getEmail()
        );
    }
}
