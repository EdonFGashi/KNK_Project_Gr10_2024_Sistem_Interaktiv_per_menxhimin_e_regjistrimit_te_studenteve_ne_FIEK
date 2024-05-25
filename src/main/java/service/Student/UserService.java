package service.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import model.dto.Admin.ChangePasswordOnDb;
import model.dto.Overall.ChangePasswordDto;
import model.dto.Overall.CreateUserDto;
//import model.dto.Overall.LoginUserDto;
import model.dto.Overall.UserDto;
import model.ApplicationStatus;
import model.dto.Student.EditUserProfileDto;
import model.dto.Student.UserProfileDto;
import repository.UserRepository;
import service.CustomExceptions.InvalidEmail;
import service.CustomExceptions.InvalidPassword;
import service.PasswordHasher;

import java.sql.SQLException;

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

     public static void saveAplicStatus(ApplicationStatus appstatus) {
        UserRepository.saveApplicationStatus(appstatus);
    }
    public static ObservableList<ApplicationStatus> getApplicationsForUser(int userID) {
         return FXCollections.observableArrayList(UserRepository.getApplicationsForUser(userID));
    }




    public static void changePassword(ChangePasswordDto changeData) throws InvalidPassword{
        User user = UserRepository.getByEmail(changeData.getEmail());

        if(user == null){
            throw new InvalidPassword("User is not found");
        }

        if(!user.getPasswordHash().equals(PasswordHasher.generateSaltedHash(changeData.getCurrentPassword(),user.getSalt()))){
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

        String saltedHashed = PasswordHasher.generateSaltedHash(changeData.getNewPassword(),user.getSalt());

        if(!UserRepository.changePassword(new ChangePasswordOnDb(user.getEmail(), saltedHashed))){
            throw new InvalidPassword("Database Connection failed");
        };
    }

}
