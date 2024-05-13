package service.Overall;

import model.Admin;
import model.SupervisorTableModel;
import model.dto.Overall.LoginDto;
import repository.AdminRepository;
import repository.Supervisor.SupervisorRepository;
import service.PasswordHasher;

public class LoginService {
    public static boolean login(LoginDto loginDto){
        String email = loginDto.getUserEmail();
        if (!email.contains("@")){
            System.out.println("Nuk eshte email valid!");
            return false;
        }

        String emailDomain = email.substring(email.indexOf("@"));

        if (emailDomain.equals("@admin.uni-pr.edu")){
//            Kodi per admin
            System.out.println("Eshte admin");

            Admin admin = AdminRepository.getByEmail(loginDto.getUserEmail());
            if (admin == null){
                System.out.println("Admini nuk u gjend ne databaze");
                return false;
            } else {
                System.out.println("Admini u gjend!");
            }

        } else if (emailDomain.equals("@uni-pr.edu")){
//            Kodi per mbikqyres
            System.out.println("Eshte mbikqyres");

            SupervisorTableModel supervisor = SupervisorRepository.getSupervisorByEmail(loginDto.getUserEmail());
            if (supervisor == null){
                System.out.println("Mbikqyresi nuk u gjend ne databaze");
                return false;
            }

            String password = loginDto.getUserPassword();
            String salt = supervisor.getSalt();
            String passwordHash = supervisor.getPasswordHash();

            return PasswordHasher.compareSaltedHash(
                    password, salt, passwordHash
            );


        } else if (emailDomain.equals("@student.uni-pr.edu")){
//            Kodi per student
            System.out.println("Eshte student");




        } else {
            System.out.println("Domeni nuk i perket fakultetit!");
        }

        System.out.println(email.substring(email.indexOf("@")));

        return false;
    }
}
