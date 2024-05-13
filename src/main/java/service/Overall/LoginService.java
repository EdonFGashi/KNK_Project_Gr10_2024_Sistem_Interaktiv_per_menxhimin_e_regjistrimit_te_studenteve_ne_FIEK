package service.Overall;

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
            System.out.println("Nuk eshte email valid!");
            return false;
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
                throw new InvalidEmail("Email not Valid");
            }
        }

//        return false;
    }


    private static boolean isValidEmail(String email){
        return email.contains("@");
    }

    private static boolean loginAsAdmin(LoginDto loginDto) throws InvalidPassword {
        System.out.println("Eshte admin");

        Admin admin = AdminRepository.getByEmail(loginDto.getUserEmail());
        if (admin == null){

            System.out.println("Admini nuk u gjend ne databaze");
            throw new InvalidPassword("Admin is not found");
//            return false;
        }

        System.out.println("Admini u gjend!");
        return true;
    }

    private static boolean loginAsSupervisor(LoginDto loginDto) throws InvalidPassword {
        System.out.println("Eshte mbikqyres");

        SupervisorTableModel supervisor = SupervisorRepository.getSupervisorByEmail(loginDto.getUserEmail());
        if (supervisor == null){
            throw new InvalidPassword("Supervisor is not found");
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
