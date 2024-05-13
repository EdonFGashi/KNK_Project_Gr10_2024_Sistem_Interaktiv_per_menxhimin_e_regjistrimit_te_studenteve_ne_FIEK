package service.Overall;

import app.Navigatior;
import model.Admin;
import model.SupervisorTableModel;
import model.dto.Overall.LoginDto;
import repository.AdminRepository;
import repository.Supervisor.SupervisorRepository;
import service.CustomExceptions.InvalidEmail;
import service.CustomExceptions.InvalidPassword;
import service.PasswordHasher;

public class LoginService {

    public static final String ADMIN_EMAIL_DOMAIN = "@admin.uni-pr.edu";
    public static final String SUPERVISOR_EMAIL_DOMAIN = "@uni-pr.edu";
    public static final String STUDENT_EMAIL_DOMAIN = "@student.uni-pr.edu";


    public static boolean login(LoginDto loginDto) throws InvalidEmail, InvalidPassword {
        String email = loginDto.getUserEmail();
        if (!isValidEmail(email)){
            throw new InvalidEmail("Invalid Email");
//            return false;
        }

        String emailDomain = email.substring(email.indexOf("@"));

        switch (emailDomain) {
            case ADMIN_EMAIL_DOMAIN -> {
                return loginAsAdmin(loginDto);
            }
            case SUPERVISOR_EMAIL_DOMAIN -> {
                return loginAsSupervisor(loginDto);
            }
            case STUDENT_EMAIL_DOMAIN -> {
                return loginAsStudent(loginDto);
            }
            default -> {
//
                throw new InvalidEmail("Email doesn't exist");
            }
        }

//        return false;
    }


    private static boolean isValidEmail(String email){
        return email.contains("@");
    }

    private static boolean loginAsAdmin(LoginDto loginDto) throws InvalidEmail {
        System.out.println("Eshte admin");

        Admin admin = AdminRepository.getByEmail(loginDto.getUserEmail());
        if (admin == null){

            System.out.println("Admini nuk u gjend ne databaze");
            throw new InvalidEmail("Admin is not found");
//            return false;
        }

        System.out.println("Admini u gjend!");

        String password = loginDto.getUserPassword();
        String salt = admin.getSalt();
        String passwordHash = admin.getHashedPassword();

        return PasswordHasher.compareSaltedHash(
                password, salt, passwordHash
        );
    }

    private static boolean loginAsSupervisor(LoginDto loginDto) throws InvalidEmail {
        System.out.println("Eshte mbikqyres");

        SupervisorTableModel supervisor = SupervisorRepository.getSupervisorByEmail(loginDto.getUserEmail());
        if (supervisor == null){
            throw new InvalidEmail("Supervisor is not found");
//            return false;
        }

        System.out.println("Mbikqyresi u gjend!");

        String password = loginDto.getUserPassword();
        String salt = supervisor.getSalt();
        String passwordHash = supervisor.getPasswordHash();

        return PasswordHasher.compareSaltedHash(
                password, salt, passwordHash
        );
    }

    private static boolean loginAsStudent(LoginDto loginDto){
        System.out.println("Eshte student");
        return false;
    }
}
