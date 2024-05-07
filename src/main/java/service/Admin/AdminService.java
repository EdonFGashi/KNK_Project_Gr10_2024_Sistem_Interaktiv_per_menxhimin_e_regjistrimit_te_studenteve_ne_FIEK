package service.Admin;


import model.Admin;
import model.dto.Admin.ChangePasswordOnDb;
import model.dto.Admin.LoginAdminDto;
import model.dto.Overall.ChangePasswordDto;
import repository.AdminRepository;
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


    public static boolean changePassword(ChangePasswordDto changeData) {
    Admin admin = AdminRepository.getByEmail(changeData.getEmail());

    if(admin == null){
        System.out.println("Admin is null");
        return false;

    }
    if(!admin.getHashedPassword().equals(PasswordHasher.generateSaltedHash(changeData.getCurrentPassword(),admin.getSalt()))){
        return false;
    }
    if(!changeData.getNewPassword().equals(changeData.getConfirmPassword())){
        return false;
    }
    if(changeData.getCurrentPassword().equals(changeData.getNewPassword())) {
       return false;
    }

    String saltedHashed = PasswordHasher.generateSaltedHash(changeData.getNewPassword(),admin.getSalt());

    return AdminRepository.changePassword(new ChangePasswordOnDb(admin.getEmail(), saltedHashed));
    }
}
