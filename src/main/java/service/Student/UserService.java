package service.Student;

import model.Admin;
import model.User;
import model.dto.Admin.AdminProfileToControllerDto;
import model.dto.Admin.EditAdminProfileDto;
import model.dto.Overall.CreateUserDto;
//import model.dto.Overall.LoginUserDto;
import model.dto.Overall.UserDto;
import model.dto.Student.EditUserProfileDto;
import model.dto.Student.UserProfileDto;
import repository.AdminRepository;
import repository.StudentApplicant.StudentApplicantRepository;
import repository.UserRepository;
import service.PasswordHasher;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    public static boolean signUp(UserDto userData) throws SQLException {
        System.out.println("Te service");
        String password = userData.getPassword();
        String confirmPassword = userData.getConfirmPassword();

        if (!password.equals(confirmPassword)) {
            return false;
        }

        String salt = PasswordHasher.generateSalt();
        String passwordHash = PasswordHasher.generateSaltedHash(
                password, salt
        );

        CreateUserDto createUserData = new CreateUserDto(
                userData.getUsername(),
                userData.getEmail(),
                salt,
                passwordHash
        );

        return UserRepository.create(createUserData);
    }


    
    public static User getUserByEmail(String email){
        return UserRepository.getByEmail(email);
    }

    /*
    public static boolean login(LoginUserDto loginData) {
        User user = UserRepository.getByEmail(loginData.getEmail());
        if (user == null) {
            return false;
        }

        String password = loginData.getPassword();
        String salt = user.getSalt();
        String passwordHash = user.getPasswordHash();

        return PasswordHasher.compareSaltedHash(
                password, salt, passwordHash
        );
    }
    */

    public static UserProfileDto getProfileInfo(String email) {
        User user = UserRepository.getByEmail(email);
        if (user == null) {
            return null;
        }
        System.out.println("user eshte marre");
        return new UserProfileDto(
                user.getUsername(),
                user.getEmail()

        );
    }

    public static boolean savePersonalDetails(EditUserProfileDto editData) {
        return UserRepository.savePersonalDetails(editData);
    }

     public static void saveAplicStatus(ApplicationStatusDto appstatus) {
        UserRepository.saveApplicationStatus(appstatus);
    }

}
