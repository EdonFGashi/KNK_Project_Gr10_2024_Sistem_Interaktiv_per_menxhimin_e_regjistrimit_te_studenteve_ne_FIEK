package service.Student;

import model.User;
import model.dto.Overall.CreateUserDto;
import model.dto.Overall.LoginUserDto;
import model.dto.Overall.UserDto;
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
                userData.getFirstName(),
                userData.getLastName(),
                userData.getEmail(),
                salt,
                passwordHash
        );

        return UserRepository.create(createUserData);
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
     
}
