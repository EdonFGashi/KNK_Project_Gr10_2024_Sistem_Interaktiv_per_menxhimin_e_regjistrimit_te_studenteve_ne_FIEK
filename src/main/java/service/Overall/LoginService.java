package service.Overall;

import app.Navigatior;
import controller.SESSION;
import javafx.stage.Stage;
import model.Admin;
import model.SupervisorTableModel;
import model.User;
import model.dto.Overall.LoginDto;
import repository.AdminRepository;
import repository.Supervisor.SupervisorRepository;
import repository.UserRepository;
import service.CustomExceptions.InvalidEmail;
import service.CustomExceptions.InvalidPassword;
import service.PasswordHasher;

public class LoginService {

    public static final String ADMIN_EMAIL_DOMAIN = "@admin.uni-pr.edu";
    public static final String SUPERVISOR_EMAIL_DOMAIN = "@uni-pr.edu";
    public static final String STUDENT_EMAIL_DOMAIN = "@student.uni-pr.edu";


    public static String login(LoginDto loginDto) throws InvalidEmail, InvalidPassword {
        String email = loginDto.getUserEmail();
        if (!isValidEmail(email)){
            throw new InvalidEmail("Invalid Email Format");
        }

        String password = loginDto.getUserPassword();
        if (password.isEmpty()){
            throw new InvalidPassword("Please type your password!");
        }

        String emailDomain = email.substring(email.indexOf("@"));

        switch (emailDomain) {
            case ADMIN_EMAIL_DOMAIN -> {
                if (loginAsAdmin(loginDto)){
                    SESSION.setLoggedUserEmail(email);
                    SESSION.setUser(1);
                    return "admin";
                }
                return null;
            }
            case SUPERVISOR_EMAIL_DOMAIN -> {
                if (loginAsSupervisor(loginDto)){
                    SESSION.setLoggedUserEmail(email);
                    SESSION.setUser(2);
                    return "supervisor";
                }
                return null;
            }

            default -> {
//
                if (loginAsStudent(loginDto)){
                    SESSION.setLoggedUserEmail(email);
                    SESSION.setUser(3);
                    return "student";
                }
                return null;
            }
        }
    }

    private static boolean isValidEmail(String email){
        String emailPattern = "^.+@.+\\..+$";
        // Kontrollon nese emaili i pershtatet patternit
        return email.matches(emailPattern);
    }

    private static boolean loginAsAdmin(LoginDto loginDto) throws InvalidEmail, InvalidPassword {

        Admin admin = AdminRepository.getByEmail(loginDto.getUserEmail());

        if (admin == null){

            return false;
        }

        String password = loginDto.getUserPassword();

        String salt = admin.getSalt();
        String passwordHash = admin.getHashedPassword();

        return PasswordHasher.compareSaltedHash(
                password, salt, passwordHash
        );
    }

    private static boolean loginAsSupervisor(LoginDto loginDto) throws InvalidEmail {

        SupervisorTableModel supervisor = SupervisorRepository.getSupervisorByEmail(loginDto.getUserEmail());
        if (supervisor == null){

            return false;
        }

        String password = loginDto.getUserPassword();
        String salt = supervisor.getSalt();
        String passwordHash = supervisor.getPasswordHash();

        return PasswordHasher.compareSaltedHash(
                password, salt, passwordHash
        );
    }

    private static boolean loginAsStudent(LoginDto loginDto){
        User user = UserRepository.getByEmail(loginDto.getUserEmail());

        if (user == null){

            return false;
        }

        String password = loginDto.getUserPassword();

        String salt = user.getSalt();
        String passwordHash = user.getPasswordHash();

        return PasswordHasher.compareSaltedHash(
                password, salt, passwordHash
        );
    }
}
