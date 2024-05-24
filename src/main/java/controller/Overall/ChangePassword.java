package controller.Overall;

import app.Navigatior;
import app.PopUp;
import controller.SESSION;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.util.Duration;
import model.dto.Overall.ChangePasswordDto;
import service.Admin.AdminService;
import service.CustomExceptions.InvalidPassword;
import service.Student.StudentService;
import service.Supervisor.SupervisorService;

public class ChangePassword {
    @FXML
    private PasswordField pwdCurrentPassword;
    @FXML
    private PasswordField pwdNewPassword;
    @FXML
    private PasswordField pwdConfirmPassword;

    public static String email;

    @FXML
    private void handleChangePassword(ActionEvent ae){

        ChangePasswordDto change = new ChangePasswordDto(
                this.pwdCurrentPassword.getText(),
                this.pwdNewPassword.getText(),
                this.pwdConfirmPassword.getText(),
//                SESSION.getLoggedAdmin().getEmail()
                SESSION.getLoggedUserEmail()
        );

        try{
            int user = SESSION.getUser();
            if (user == 1){
                AdminService.changePassword(change);
            } else if (user == 2){
                SupervisorService.changePassword(change);
            } else if (user == 3){
                System.out.println(" ");
            } else {
                System.out.println("ERROR");
            }

            System.out.println("U nderrua Passwordi me sukses");
            Navigatior.closeStageAfterDelay(ae, Duration.millis(3000));
            PopUp.loading("   Changed Password!",true, "");
        }catch(InvalidPassword e){
            PopUp.loading(e.getMessage(),false, "");
        }

    }



}
