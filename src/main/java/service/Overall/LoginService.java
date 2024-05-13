package service.Overall;

import model.Admin;
import model.dto.Overall.LoginDto;
import repository.AdminRepository;

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
            System.out.println("Eshte mbikqyres");
//            Kodi per mbikqyres


        } else if (emailDomain.equals("@student.uni-pr.edu")){
            System.out.println("Eshte student");
//            Kodi per student



        } else {
            System.out.println("Domeni nuk i perket fakultetit!");
        }

        System.out.println(email.substring(email.indexOf("@")));

        return false;
    }
}
