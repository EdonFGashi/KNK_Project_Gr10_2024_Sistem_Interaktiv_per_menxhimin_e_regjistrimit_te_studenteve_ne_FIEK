package service.Student;

import model.Admin;
import model.User;
import model.dto.Admin.AdminProfileToControllerDto;
import model.dto.Admin.EditAdminProfileDto;
import model.dto.Overall.CreateUserDto;
//import model.dto.Overall.LoginUserDto;
import model.dto.Overall.UserDto;
import model.dto.Student.ApplicationStatusDto;
import model.dto.Student.EditUserProfileDto;
import model.dto.Student.UserProfileDto;
import repository.AdminRepository;
import repository.StudentApplicant.StudentApplicantRepository;
import repository.UserRepository;
import service.CustomExceptions.InvalidEmail;
import service.CustomExceptions.InvalidPassword;
import service.PasswordHasher;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    public static boolean signUp(UserDto userData) throws SQLException, InvalidPassword, InvalidEmail {
        System.out.println("Te service");
        String password = userData.getPassword();
        String confirmPassword = userData.getConfirmPassword();
        String email = userData.getEmail();


        if (!isValidEmail(email)){
            System.out.println("Incorrect email format");
            throw new InvalidEmail("Incorrect email format");
        }
        System.out.println("Email is valid");
        if (password.length() < 8){
            throw new InvalidPassword("Password too short");
        }

        System.out.println("Password length is good");
        if (!password.equals(confirmPassword)) {
            throw new InvalidPassword("Passwords do not match");
        }

        System.out.println("Passwords do match");

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


    private static boolean isValidEmail(String email){
        String emailPattern = "^.+@.+\\..+$";
        // Kontrollon nese emaili i pershtatet patternit
        return email.matches(emailPattern);
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
