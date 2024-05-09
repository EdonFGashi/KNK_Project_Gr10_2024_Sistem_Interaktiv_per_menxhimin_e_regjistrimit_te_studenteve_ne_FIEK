package service.Supervisor;

import model.dto.Supervisor.SupervisorCreateInterfaceDto;
import model.dto.Supervisor.SupervisorCreateModelDto;
import model.dto.Supervisor.SupervisorTableModel;
import repository.Supervisor.SupervisorRepository;
import service.PasswordHasher;

import java.sql.SQLException;
import java.util.Date;

public class SupervisorService {
    public static boolean signUp(SupervisorCreateInterfaceDto supervisorData) {
        String password = supervisorData.getPassword();
        String confirmPassword = supervisorData.getConfirmPassword();

        if(!password.equals(confirmPassword)){
            return false;
        }


        String salt = PasswordHasher.generateSalt();
        String passwordHash = PasswordHasher.generateSaltedHash(password, salt);

        SupervisorCreateModelDto superVisorNewRecord = new SupervisorCreateModelDto(
                supervisorData.getFirstName(),
                supervisorData.getLastName(),
                supervisorData.getEmail(),
                salt,
                passwordHash
        );

        try {
            return SupervisorRepository.create(superVisorNewRecord);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean supervisorIsFoundByEmail(String email){
        SupervisorTableModel supervisor = SupervisorRepository.getSupervisorByEmail(email);
        if(supervisor == null){
            return false;
        }
        return true;
    }
}
